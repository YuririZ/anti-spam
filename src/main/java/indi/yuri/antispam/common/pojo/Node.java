package indi.yuri.antispam.common.pojo;

import lombok.Data;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author yurizhang
 * @date 2020/9/1 18:12
 */
@Data
public class Node {
    /**
     * 节点名称
     */
    private String nodeName;
    /**
     * 节点路径
     */
    private String path;
    /**
     * 子节点
     */
    private Map<String, Node> subNodeMap;
    /**
     * 判断是否为结束节点
     */
    private Boolean end;

    /**
     * 添加节点
     *
     * @param node
     */
    public void addNode(Node node){
        if (node == null){
            return;
        }
        if (this.subNodeMap == null){
            this.subNodeMap = new HashMap<>();
        }
        this.subNodeMap.put(node.getNodeName(), node);
    }

    /**
     * 添加节点
     *
     * @param word 敏感词
     */
    public void addNode(String word) {
        // 空串则当前节点为结束节点
        if (StringUtils.isEmpty(word)) {
            this.end = true;
            return;
        }
        // 取输入的首个字符作为nodeName
        String nodeName = StringUtils.left(word, 1);
        // 除首字符以外剩下的字符作为subWord
        String subWord = StringUtils.substring(word, 1);
        // 如果当前节点存在索引为nodeName的子节点
        Node matchedNode = this.subNodeMap.get(nodeName);
        if (matchedNode != null) {
            matchedNode.addNode(subWord);
            return;
        }
        // 节点路径
        String path = StringUtils.join(this.path, nodeName);
        // 创建一个新的节点
        Node subNode = new Node();
        subNode.setNodeName(nodeName);
        subNode.setPath(path);
        subNode.setSubNodeMap(new HashMap<>());
        subNode.setEnd(false);
        // 父节点的子节点map修改
        this.subNodeMap = MapUtils.isEmpty(this.subNodeMap) ? new HashMap<>() : this.subNodeMap;
        this.subNodeMap.put(nodeName, subNode);
        subNode.addNode(subWord);
    }

    /**
     * 匹配节点
     *
     * @param input
     * @param rootNode
     * @return
     */
    public static Set<String> matchNodes(String input, Node rootNode){
        Set<String> matchedNodeSet = new HashSet<>();
        while (StringUtils.isNotEmpty(input)){
            matchNodes(input, rootNode, matchedNodeSet);
            input = StringUtils.substring(input, 1);
        }
        return matchedNodeSet;
    }

    /**
     * 匹配节点
     *
      * @param input
     * @param rootNode
     * @param matchedNodeSet
     * @return
     */
    public static void matchNodes(String input, Node rootNode, Set<String> matchedNodeSet) {
        if (StringUtils.isEmpty(input) || rootNode == null) {
            return;
        }
        // 取输入的首个字符作为nodeName
        String nodeName = StringUtils.left(input, 1);
        // 除首字符以外剩下的字符作为subWord
        String subWord = StringUtils.substring(input, 1);
        // 如果当前节点存在索引为nodeName的子节点
        Node matchedNode = rootNode.subNodeMap.get(nodeName);
        if (matchedNode == null){
            return;
        }
        matchNodes(subWord, matchedNode, matchedNodeSet);
        if (matchedNode.getEnd()) {
            matchedNodeSet.add(matchedNode.getPath());
        }
        return;
    }
}
