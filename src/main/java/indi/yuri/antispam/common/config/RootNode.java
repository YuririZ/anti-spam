package indi.yuri.antispam.common.config;

import indi.yuri.antispam.common.pojo.Node;
import indi.yuri.antispam.common.util.ClassPathReaderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

import static indi.yuri.antispam.common.constant.AntiSpamConstant.BAD_WORDS_FILE_PATH;
import static indi.yuri.antispam.common.constant.AntiSpamConstant.BAD_WORDS_NODE_ROOT_NAME;

/**
 * @author yurizhang
 * @date 2020/9/14 18:27
 */
@Component
public class RootNode {
    private Node rootNode;

    @Autowired
    public RootNode(){
        this.rootNode = new Node();
        rootNode.addNode(initBadWordNode());
    }

    /**
     * 初始化敏感词node
     *
     * @return
     */
    private Node initBadWordNode(){
        Node node = new Node();
        node.setEnd(false);
        node.setNodeName(BAD_WORDS_NODE_ROOT_NAME);
        node.setSubNodeMap(Collections.emptyMap());
        List<String> badWordsList = ClassPathReaderUtils.getContentList(BAD_WORDS_FILE_PATH);
        for (String badWord : badWordsList) {
            node.addNode(badWord);
        }
        return node;
    }

    /**
     * 通过节点名称获取节点
     *
     * @param nodeName
     * @return
     */
    public Node getNodeByName(String nodeName){
        return this.rootNode.getSubNodeMap().get(nodeName);
    }
}
