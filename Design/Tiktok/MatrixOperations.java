package Tiktok;

import java.util.*;

public class MatrixOperations {
  // 用于存储矩阵的元素
  private Map<Integer, Map<Integer, Set<String>>> matrix;
  // 用于记录撤销操作
  private Stack<Operation> undoStack;

  public MatrixOperations() {
    matrix = new HashMap<>();
    undoStack = new Stack<>();
  }

  // 向指定位置添加元素
  public void add(int row, int col, String element) {
    matrix.putIfAbsent(row, new HashMap<>());
    matrix.get(row).putIfAbsent(col, new HashSet<>());
    matrix.get(row).get(col).add(element);

    // 记录操作以便撤销
    undoStack.push(new Operation("add", row, col, element));
  }

  // 删除指定位置的元素
  public void delete(int row, int col, String element) {
    if (matrix.containsKey(row) && matrix.get(row).containsKey(col)) {
      matrix.get(row).get(col).remove(element);

      // 记录操作以便撤销
      undoStack.push(new Operation("delete", row, col, element));
    }
  }

  // 查询指定位置的元素
  public boolean contains(int row, int col, String element) {
    return matrix.containsKey(row) && matrix.get(row).containsKey(col) && matrix.get(row).get(col).contains(element);
  }

  // 撤销上一个操作
  public void undo() {
    if (!undoStack.isEmpty()) {
      Operation lastOp = undoStack.pop();

      // 根据操作类型撤销操作
      if (lastOp.type.equals("add")) {
        // 直接删除该元素，而不是调用 delete()，避免重复记录撤销操作
        if (matrix.containsKey(lastOp.row) && matrix.get(lastOp.row).containsKey(lastOp.col)) {
          matrix.get(lastOp.row).get(lastOp.col).remove(lastOp.element);
        }
      } else if (lastOp.type.equals("delete")) {
        // 直接添加该元素，而不是调用 add()，避免重复记录撤销操作
        matrix.putIfAbsent(lastOp.row, new HashMap<>());
        matrix.get(lastOp.row).putIfAbsent(lastOp.col, new HashSet<>());
        matrix.get(lastOp.row).get(lastOp.col).add(lastOp.element);
      }
    }
  }

  // 打印矩阵状态（辅助方法）
  public void printMatrix() {
    for (Map.Entry<Integer, Map<Integer, Set<String>>> rowEntry : matrix.entrySet()) {
      for (Map.Entry<Integer, Set<String>> colEntry : rowEntry.getValue().entrySet()) {
        System.out.println("Row: " + rowEntry.getKey() + ", Col: " + colEntry.getKey() + ", Elements: " + colEntry.getValue());
      }
    }
  }

  // 用于记录操作的信息
  private class Operation {
    String type; // 操作类型 (add 或 delete)
    int row; // 行号
    int col; // 列号
    String element; // 被操作的元素

    public Operation(String type, int row, int col, String element) {
      this.type = type;
      this.row = row;
      this.col = col;
      this.element = element;
    }
  }

  // 测试方法
  public static void main(String[] args) {
    MatrixOperations mo = new MatrixOperations();

    // 执行一些操作
    mo.add(1, 1, "A");
    mo.add(1, 1, "B");
    mo.add(2, 2, "C");

    // 打印当前矩阵状态
    System.out.println("Current Matrix:");
    mo.printMatrix();

    // 撤销上次操作
    mo.undo();
    System.out.println("After undo:");
    mo.printMatrix();

    // 删除元素
    mo.delete(1, 1, "A");
    System.out.println("After deletion:");
    mo.printMatrix();

    // 撤销删除操作
    mo.undo();
    System.out.println("After undoing deletion:");
    mo.printMatrix();
  }
}
