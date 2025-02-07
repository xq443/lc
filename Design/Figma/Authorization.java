package Figma;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Authorization {
  // 团队资源，顶层资源。每个团队包含一些文件夹，并记录了显式授权用户。
  class Team {
    final String id;         // 全局唯一标识符
    final List<String> folderIds;  // 团队下的子文件夹id列表
    final List<String> userIds;    // 拥有显式权限的用户id列表

    public Team(String id, List<String> folderIds, List<String> userIds) {
      this.id = id;
      this.folderIds = folderIds;
      this.userIds = userIds;
    }
  }

  // 文件夹资源，可以嵌套其他文件夹和文件，每个文件夹有一个父资源（团队或文件夹）
  class Folder {
    final String id;         // 文件夹的唯一标识符
    final List<String> folderIds; // 子文件夹id列表
    final List<String> fileIds;   // 子文件id列表
    final List<String> userIds;   // 拥有显式权限的用户id列表
    final String parentId;        // 父资源id，可能是团队id或文件夹id

    public Folder(String id, List<String> folderIds, List<String> fileIds, List<String> userIds, String parentId) {
      this.id = id;
      this.folderIds = folderIds;
      this.fileIds = fileIds;
      this.userIds = userIds;
      this.parentId = parentId;
    }
  }

  // 文件资源，是叶子节点，每个文件有一个父文件夹id
  class FigFile {
    final String id;       // 文件的唯一标识符
    final List<String> userIds;  // 拥有显式权限的用户id列表
    final String parentId;       // 父文件夹id

    public FigFile(String id, List<String> userIds, String parentId) {
      this.id = id;
      this.userIds = userIds;
      this.parentId = parentId;
    }
  }

  class ResourceAnalyzer {
    // 用于保存所有团队、文件夹、文件数据
    private List<Team> tm;            // 团队列表，变量名"tm"长度为2
    private Map<String, Folder> fd;   // 文件夹映射：id -> Folder，变量名"fd"
    private Map<String, FigFile> ff;  // 文件映射：id -> FigFile，变量名"ff"

    /**
     * 构造函数接收来自服务器的无序团队、文件夹和文件数据。
     * 为了方便后续查找，构建了文件夹和文件的映射。
     */
    public ResourceAnalyzer(List<Team> teams, List<Folder> folders, List<FigFile> files) {
      // 保存团队数据
      this.tm = teams;
      // 构建文件夹映射
      this.fd = new HashMap<>();
      for (Folder f : folders) {
        fd.put(f.id, f);
      }
      // 构建文件映射
      this.ff = new HashMap<>();
      for (FigFile f : files) {
        ff.put(f.id, f);
      }
    }

    /**
     * 获取用户拥有权限的最顶层资源集合。
     * 也就是说，如果用户对某个资源有权限，并且该资源的父资源（或更高层）也有权限，
     * 则只返回较高层的那个资源。
     *
     * @param u 用户id
     * @return 包含顶层资源id的集合
     */
    public Set<String> getTopmostResourcesForUser(String u) {
      // 结果集，用于存放所有顶层资源id
      Set<String> r = new HashSet<>();
      // 遍历所有团队，从顶层开始深度优先搜索
      for (Team t : tm) {
        dfsT(t, u, r);
      }
      return r;
    }

    /**
     * 递归处理团队资源。
     * 如果用户对团队有显式权限，则将该团队id加入结果集，并停止搜索该团队下的资源；
     * 否则继续递归处理该团队下的所有文件夹。
     *
     * @param t 当前团队
     * @param u 用户id
     * @param r 结果集
     */
    private void dfsT(Team t, String u, Set<String> r) {
      // 如果用户对该团队有显式权限，则直接加入结果集
      if (t.userIds.contains(u)) {
        r.add(t.id);
        return;
      }
      // 否则遍历该团队下的所有文件夹
      if (t.folderIds != null) {
        for (String id : t.folderIds) {
          Folder f = fd.get(id);
          if (f != null) {
            dfsF(f, u, r);
          }
        }
      }
    }

    /**
     * 递归处理文件夹资源。
     * 如果用户对当前文件夹有显式权限，则将该文件夹id加入结果集，并停止搜索其下的子资源；
     * 否则继续遍历该文件夹下的所有子文件夹和文件。
     *
     * @param f 当前文件夹
     * @param u 用户id
     * @param r 结果集
     */
    private void dfsF(Folder f, String u, Set<String> r) {
      // 如果用户对当前文件夹有显式权限，则加入结果集，停止向下搜索
      if (f.userIds.contains(u)) {
        r.add(f.id);
        return;
      }
      // 遍历该文件夹下的子文件夹
      if (f.folderIds != null) {
        for (String id : f.folderIds) {
          Folder nf = fd.get(id);
          if (nf != null) {
            dfsF(nf, u, r);
          }
        }
      }
      // 遍历该文件夹下的子文件
      if (f.fileIds != null) {
        for (String id : f.fileIds) {
          FigFile fl = ff.get(id);
          if (fl != null) {
            dfsFF(fl, u, r);
          }
        }
      }
    }

    /**
     * 处理文件资源。文件是叶子节点，
     * 如果用户对文件有显式权限，则将该文件id加入结果集。
     *
     * @param f 当前文件
     * @param u 用户id
     * @param r 结果集
     */
    private void dfsFF(FigFile f, String u, Set<String> r) {
      if (f.userIds.contains(u)) {
        r.add(f.id);
      }
      // 文件为叶节点，无子资源，直接返回
    }
  }

    // 示例：
    //         Team1 (userA)
    //       /          \
    // Folder2(userB)   Folder3(userB)
    //    /              /       \
    // File4(userA)  Folder5   File6(userB)
    //
    // 测试用例中，userA显式访问Team1和File4，
    // 但Team1已经覆盖了File4，所以顶层资源只有["team1"]；
    // userB显式访问Folder2、Folder3和File6，
    // 但Folder3覆盖了File6，所以顶层资源为["folder2", "folder3"]

    // 使用Guava提供的Lists.newArrayList方法构造列表（假定已经导入相应依赖）


    public void main(String[] args) {
      List<Team> TEAMS = List.of(
          new Team("team1", Arrays.asList("folder2", "folder3"), List.of("userA"))
      );

      List<Folder> FOLDERS = Arrays.asList(
          new Folder("folder2", Collections.emptyList(), List.of("file4"), List.of("userB"), "team1"),
          new Folder("folder3", List.of("folder5"), List.of("file6"), List.of("userB"), "team1"),
          new Folder("folder5", Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), "folder3")
      );

      List<FigFile> FILES = Arrays.asList(
          new FigFile("file4", List.of("userA"), "folder2"),
          new FigFile("file6", List.of("userB"), "folder3")
      );
      ResourceAnalyzer ra = new ResourceAnalyzer(TEAMS, FOLDERS, FILES);
      System.out.println(ra.getTopmostResourcesForUser("userA"));
      System.out.println(ra.getTopmostResourcesForUser("userB"));
    }
  }

