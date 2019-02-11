package in.hocg.manager.model.vo;

import in.hocg.mybatis.module.system.entity.Resource;
import in.hocg.mybatis.module.system.entity.Role;
import in.hocg.scaffold.support.basis.parameter.BaseParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;

/**
 * @author hocgin
 */
@Slf4j
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class RoleDetailVO extends Role
        implements BaseParameter {
    
    /**
     * 角色所分配的资源
     */
    private Collection<Resource> resources;
    
    @Override
    public Object fill(Object object) {
        BeanUtils.copyProperties(object, this);
        return this;
    }
    
    public static void main(String[] args) {
        /**
         *          [1, 12]
         *    [2, 5]       [6, 11]
         *    [3, 4]    [7, 8][9, 10]
         */
        int[][] arr = {
                {2, 5},
                {1, 12},
                {6, 11},
                {3, 4},
                {7, 8},
                {9, 10}
        };
        jj(arr);
    }
    
    public static void jj(int[][] args) {
        List<int[]> sorted = Stream.of(args)
                .sorted(comparingInt(a -> a[0]))
                .collect(Collectors.toList());
        
        ArrayList<int[]> reverse = new ArrayList<>(sorted);
        Collections.reverse(reverse);
    
        for (int[] ints1 : reverse) {
            int lft = ints1[0];
            int rgt = ints1[1];
            for (int[] ints : reverse) {
                if (lft > ints[0] && rgt < ints[1]) {
                    log.debug("{}选择{}为父节点", Arrays.toString(ints1), Arrays.toString(ints));
                    break;
                }
            }
        }
        
        System.out.println(sorted);
    
        return;
    }
}
