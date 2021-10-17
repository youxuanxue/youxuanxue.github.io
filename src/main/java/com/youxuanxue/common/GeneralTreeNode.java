package com.youxuanxue.common;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Copyright @2021 xuejiao
 *
 * @author xuejiao <forsurexue@gmail.com>
 * 2021/10/17
 */
public class GeneralTreeNode {
    public String value = null;
    public GeneralTreeNode father = null;
    public List<GeneralTreeNode> children = null;


    /**
     * DFS = Depth First Search
     *
     * @return String of tree nodes with DFS order
     */
    public String dfs() {
        Stack<GeneralTreeNode> stack = new Stack<>();
        stack.push(this);

        StringBuffer buffer = new StringBuffer();
        while (!stack.isEmpty()) {
            GeneralTreeNode node = stack.pop();
            if (node.value != null)
                buffer.append(node.value);
            if (node.children != null) {
                stack.addAll(node.children);
            }
        }

        System.out.println(String.format("bfs result: %s", buffer.toString()));

        return buffer.toString();
    }

    /**
     * BFS = Breath First Search
     *
     * @return String of tree nodes with BFS order
     */
    public String bfs() {
        LinkedList<GeneralTreeNode> queue = new LinkedList<>();
        queue.add(this);

        StringBuffer buffer = new StringBuffer();
        while (!queue.isEmpty()) {
            GeneralTreeNode node = queue.pop();
            if (node.value != null)
                buffer.append(node.value);
            if (node.children != null) {
                queue.addAll(node.children);
            }
        }

        System.out.println(String.format("dfs result: %s", buffer.toString()));

        return buffer.toString();
    }
}
