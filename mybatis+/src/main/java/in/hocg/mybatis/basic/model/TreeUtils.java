package in.hocg.mybatis.basic.model;

import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;

import java.util.List;

/**
 * Created by hocgin on 2019/1/16.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class TreeUtils {
    
    /**
     * 构建树
     *
     * @param root
     * @param nodes
     * @return
     */
    public static <T extends NodeModel> T buildTree(T root, List<T> nodes) {
        int rgt = root.getRgt();
        int lft = root.getLft();
        Integer depth = root.getDepth();
        nodes.stream()
                .filter(node -> {
                    int nodeRgt = node.getRgt();
                    int nodeLft = node.getLft();
                    return depth == (node.getDepth() - 1)
                            && rgt > nodeRgt
                            && lft < nodeLft;
                })
                .forEach(node -> {
                    List<T> children = root.getChildren();
                    children.add(buildTree(node, nodes));
                });
        return root;
    }
    
    /**
     * 构建树
     *
     * @param nodes
     * @param <T>
     * @return
     */
    public static <T extends NodeModel> T buildTree(List<T> nodes) {
        T root = nodes.get(0);
        return buildTree(root, nodes);
    }
    
    /**
     * 遍历树
     *
     * @param parent   父节点
     * @param children 子节点列表
     * @param <T>
     */
    public static <T extends NodeModel> void traversing(T parent,
                                                        List<T> children,
                                                        Operating<T> operating) {
        if (children != null
                && !children.isEmpty()) {
            children.forEach((child) -> {
                operating.run(parent, child);
                traversing(child, child.getChildren(), operating);
            });
        }
    }
    
    /**
     * 抹除节点位置信息
     *
     * @param node
     * @param <T>
     * @return
     */
    public static <T extends NodeModel> T erase(T node) {
        node.setLft(0);
        node.setRgt(0);
        node.setChildren(Lists.newArrayList());
        return node;
    }
    
    public interface Operating<T extends NodeModel> {
        void run(T parent, T node);
    }
}
