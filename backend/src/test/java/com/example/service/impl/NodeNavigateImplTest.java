package com.example.service.impl;

import com.example.entity.dto.NodeRelative;
import com.example.service.NodeService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class NodeNavigateImplTest {
    private final String typeShort = "short";
    private final String typeTime = "time";
    @Resource
    NodeNavigateImpl nodeNavigate;

    @BeforeEach
    void setUp() {
        System.out.println("测试开始");
    }

    @Test
    void testDemo() {
        List<Integer> list = nodeNavigate.findShortestPathWithSome("dfd73 jdj47 jdjd36", typeShort);
        System.out.println(list);
    }

    /**
     * 测试包含非数字字符的字符串
     */
    @Test
    void testFindShortestPathWithTwo_invalidString1() {
        System.out.println("测试包含非数字字符的字符串");
        List<Integer> list = nodeNavigate.findShortestPathWithTwo("dadad1", "dadeee27", typeShort);
        assertTrue(list != null && list.isEmpty(), "非法输入应返回空列表或null");
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试有效的from和to字符串
     */
    @Test
    void testFindShortestPathWithTwo_validString1() {
        System.out.println("测试有效的from和to字符串");
        List<Integer> list = nodeNavigate.findShortestPathWithTwo("address10", "location27", typeShort);
        if (list != null) System.out.println("最短路径为：" + list);
    }

    /**
     * 测试长度不足的字符串
     */
    @Test
    void testFindShortestPathWithTwo_shortString1() {
        System.out.println("测试长度不足的字符串");
        List<Integer> list = nodeNavigate.findShortestPathWithTwo("a1", "a2", typeShort);
        assertTrue(list != null && list.isEmpty(), "非法输入应返回空列表或null");
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试包含无效数字的字符串
     */
    @Test
    void testFindShortestPathWithTwo_invalidNumber1() {
        System.out.println("测试包含无效数字的字符串");
        List<Integer> list = nodeNavigate.findShortestPathWithTwo("data100", "data-1", typeShort);
        assertTrue(list != null && list.isEmpty(), "非法输入应返回空列表或null");
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试不包含数字的字符串
     */
    @Test
    void testFindShortestPathWithTwo_nonNumeric1() {
        System.out.println("测试不包含数字的字符串");
        List<Integer> list = nodeNavigate.findShortestPathWithTwo("dataaa", "datab2", typeShort);
        assertTrue(list != null && list.isEmpty(), "非法输入应返回空列表");
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试边界情况的字符串（00到99）
     */
    @Test
    void testFindShortestPathWithTwo_edgeCase1() {
        System.out.println("测试边界情况的字符串（00到99");
        List<Integer> list = nodeNavigate.findShortestPathWithTwo("node00", "node99", typeShort);
        if (list != null) System.out.println("最短路径为：" + list);
    }

    /**
     * 测试空字符串
     */
    @Test
    void testFindShortestPathWithTwo_emptyString1() {
        System.out.println("测试空字符串");
        List<Integer> list = nodeNavigate.findShortestPathWithTwo("", "", typeShort);
        assertTrue(list != null && list.isEmpty(), "非法输入应返回空列表");
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试单字符字符串
     */
    @Test
    void testFindShortestPathWithTwo_singleChar1() {
        System.out.println("测试单字符字符串");
        List<Integer> list = nodeNavigate.findShortestPathWithTwo("a", "b", typeShort);
        assertTrue(list != null && list.isEmpty(), "非法输入应返回空列表");
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试有效的from和to字符串组合
     */
    @Test
    void testFindShortestPathWithTwo_validString2() {
        System.out.println("测试有效的from和to字符串组合");
        List<Integer> list = nodeNavigate.findShortestPathWithTwo("start01", "end50", typeShort);
        if (list != null) System.out.println("最短路径为：" + list);
    }

    /**
     * 测试另一组边界情况的字符串（99到00）
     */
    @Test
    void testFindShortestPathWithTwo_edgeCase2() {
        System.out.println("测试另一组边界情况的字符串（99到00)");
        List<Integer> list = nodeNavigate.findShortestPathWithTwo("start99", "end00", typeShort);
        if (list != null) System.out.println("最短路径为：" + list);
    }

    /**
     * 测试另一组包含无效字符的字符串
     */
    @Test
    void testFindShortestPathWithTwo_invalidString2() {
        System.out.println("测试另一组包含无效字符的字符串");
        List<Integer> list = nodeNavigate.findShortestPathWithTwo("startXX", "endYY", typeShort);
        assertTrue(list != null && list.isEmpty(), "非法输入应返回空列表");
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试包含非数字字符的字符串（typeTime）
     */
    @Test
    void testFindShortestPathWithTwo_invalidString3() {
        System.out.println("测试包含非数字字符的字符串（typeTime）");
        List<Integer> list = nodeNavigate.findShortestPathWithTwo("dadad1", "dadeee27", typeTime);
        assertTrue(list != null && list.isEmpty(), "非法输入应返回空列表");
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试有效的from和to字符串（typeTime）
     */
    @Test
    void testFindShortestPathWithTwo_validString4() {
        System.out.println("测试有效的from和to字符串（typeTime）");
        List<Integer> list = nodeNavigate.findShortestPathWithTwo("address10", "location27", typeTime);
        if (list != null) System.out.println("最短路径为：" + list);
    }

    /**
     * 测试长度不足的字符串（typeTime）
     */
    @Test
    void testFindShortestPathWithTwo_shortString5() {
        System.out.println("测试长度不足的字符串（typeTime）");
        List<Integer> list = nodeNavigate.findShortestPathWithTwo("a1", "a2", typeTime);
        assertTrue(list != null && list.isEmpty(), "非法输入应返回空列表");
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试包含无效数字的字符串（typeTime）
     */
    @Test
    void testFindShortestPathWithTwo_invalidNumber2() {
        System.out.println("测试包含无效数字的字符串（typeTime）");
        List<Integer> list = nodeNavigate.findShortestPathWithTwo("data100", "data-1", typeTime);
        assertTrue(list != null && list.isEmpty(), "非法输入应返回空列表");
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试不包含数字的字符串（typeTime）
     */
    @Test
    void testFindShortestPathWithTwo_nonNumeric3() {
        System.out.println("测试不包含数字的字符串（typeTime）");
        List<Integer> list = nodeNavigate.findShortestPathWithTwo("dataaa", "datab2", typeTime);
        assertTrue(list != null && list.isEmpty(), "非法输入应返回空列表");
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试边界情况的字符串（00到99）（typeTime）
     */
    @Test
    void testFindShortestPathWithTwo_edgeCase3() {
        System.out.println("测试边界情况的字符串（00到99）（typeTime) ");
        List<Integer> list = nodeNavigate.findShortestPathWithTwo("node00", "node99", typeTime);
        if (list != null) System.out.println("最短路径为：" + list);
    }

    /**
     * 测试空字符串（typeTime）
     */
    @Test
    void testFindShortestPathWithTwo_emptyString2() {
        System.out.println("测试空字符串（typeTime）");
        List<Integer> list = nodeNavigate.findShortestPathWithTwo("", "", typeTime);
        assertTrue(list != null && list.isEmpty(), "非法输入应返回空列表");
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试单字符字符串（typeTime）
     */
    @Test
    void testFindShortestPathWithTwo_singleChar2() {
        System.out.println("测试单字符字符串（typeTime）");
        List<Integer> list = nodeNavigate.findShortestPathWithTwo("a", "b", typeTime);
        assertTrue(list != null && list.isEmpty(), "非法输入应返回空列表");
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试有效的from和to字符串组合（typeTime）
     */
    @Test
    void testFindShortestPathWithTwo_validString3() {
        System.out.println("测试有效的from和to字符串组合（typeTime）");
        List<Integer> list = nodeNavigate.findShortestPathWithTwo("start01", "end50", typeTime);
        if (list != null) System.out.println("最短路径为：" + list);
    }

    /**
     * 测试另一组边界情况的字符串（99到00）（typeTime）
     */
    @Test
    void testFindShortestPathWithTwo_edgeCase4() {
        System.out.println("测试另一组边界情况的字符串（99到00）（typeTime）");
        List<Integer> list = nodeNavigate.findShortestPathWithTwo("start99", "end00", typeTime);
        if (list != null) System.out.println("最短路径为：" + list);
    }

    /**
     * 测试另一组包含无效字符的字符串（typeTime）
     */
    @Test
    void testFindShortestPathWithTwo_invalidString4() {
        System.out.println("测试另一组包含无效字符的字符串（typeTime）");
        List<Integer> list = nodeNavigate.findShortestPathWithTwo("startXX", "endYY", typeTime);
        assertTrue(list != null && list.isEmpty(), "非法输入应返回空列表");
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试边界情况的字符串（98到99）（typeTime）
     */
    @Test
    void testFindShortestPathWithSome_edgeCase1() {
        System.out.println("测试边界情况的字符串（98到99）（typeTime）");
        List<Integer> list = nodeNavigate
                .findShortestPathWithSome("start98 end99", "time");
        if (list != null) System.out.println("最短路径为：" + list);
    }

    /**
     * 测试另一组边界情况的字符串（99到00）（typeTime）
     */
    @Test
    void testFindShortestPathWithSome_edgeCase2() {
        System.out.println("测试另一组边界情况的字符串（99到00）（typeTime）");
        List<Integer> list = nodeNavigate
                .findShortestPathWithSome("start99 end00", "time");
        if (list != null) System.out.println("最短路径为：" + list);
    }

    /**
     * 测试包含无效字符的字符串（typeTime）
     */
    @Test
    void testFindShortestPathWithSome_invalidString1() {
        System.out.println("测试包含无效字符的字符串（typeTime）");
        List<Integer> list = nodeNavigate
                .findShortestPathWithSome("startXX endYY", "time");
        assert list != null && list.isEmpty() : "非法输入应返回空列表";
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试另一组包含无效字符的字符串（typeTime）
     */
    @Test
    void testFindShortestPathWithSome_invalidString2() {
        System.out.println("测试另一组包含无效字符的字符串（typeTime）");
        List<Integer> list = nodeNavigate
                .findShortestPathWithSome("startXX endYY", "time");
        assert list != null && list.isEmpty() : "非法输入应返回空列表";
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试空字符串输入（typeTime）
     */
    @Test
    void testFindShortestPathWithSome_emptyString() {
        System.out.println("测试空字符串输入（typeTime）");
        List<Integer> list = nodeNavigate
                .findShortestPathWithSome("", "time");
        assert list != null && list.isEmpty() : "空输入应返回空列表";
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试混合有效和无效字符串（typeTime）
     */
    @Test
    void testFindShortestPathWithSome_mixedInvalidAndValid() {
        System.out.println("测试混合有效和无效字符串（typeTime）");
        List<Integer> list = nodeNavigate
                .findShortestPathWithSome("start01 endYY", "time");
        assert list != null && list.isEmpty() : "混合输入应返回空列表";
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试单个无效字符串（typeTime）
     */
    @Test
    void testFindShortestPathWithSome_singleInvalidString() {
        System.out.println("测试单个无效字符串（typeTime）");
        List<Integer> list = nodeNavigate
                .findShortestPathWithSome("invalidYY", "time");
        assert list != null && list.isEmpty() : "单个无效输入应返回空列表";
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试长度不足2的字符串（typeTime）
     */
    @Test
    void testFindShortestPathWithSome_shortString() {
        System.out.println("测试长度不足2的字符串（typeTime）");
        List<Integer> list = nodeNavigate
                .findShortestPathWithSome("a b", "time");
        assert list != null && list.isEmpty() : "长度不足的输入应返回空列表";
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试字符串中带有空格（typeTime）
     */
    @Test
    void testFindShortestPathWithSome_stringWithSpaces() {
        System.out.println("测试字符串中带有空格（typeTime）");
        List<Integer> list = nodeNavigate
                .findShortestPathWithSome("start 01 end 02", "time");
        assert list != null && list.isEmpty() : "带空格的输入应返回空列表";
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试起始和结束节点相同（typeTime）
     */
    @Test
    void testFindShortestPathWithSome_sameStartAndEnd() {
        System.out.println("测试起始和结束节点相同（typeTime）");
        List<Integer> list = nodeNavigate
                .findShortestPathWithSome("start01 start01", "time");
        if (list != null) System.out.println("最短路径为：" + list);
    }

    /**
     * 测试边界情况的字符串（98到99）（typeTime）
     */
    @Test
    void testFindShortestPathWithSome_edgeCase1_time() {
        System.out.println("测试边界情况的字符串（98到99）（typeTime）");
        List<Integer> list = nodeNavigate.findShortestPathWithSome("start98 end99", "time");
        if (list != null) System.out.println("最短路径为：" + list);
    }

    /**
     * 测试另一组边界情况的字符串（99到00）（typeTime）
     */
    @Test
    void testFindShortestPathWithSome_edgeCase2_time() {
        System.out.println("测试另一组边界情况的字符串（99到00）（typeTime）");
        List<Integer> list = nodeNavigate.findShortestPathWithSome("start99 end00", "time");
        if (list != null) System.out.println("最短路径为：" + list);
    }

    /**
     * 测试包含无效字符的字符串（typeTime）
     */
    @Test
    void testFindShortestPathWithSome_invalidString1_time() {
        System.out.println("测试包含无效字符的字符串（typeTime）");
        List<Integer> list = nodeNavigate.findShortestPathWithSome("startXX endYY", "time");
        assert list != null && list.isEmpty() : "非法输入应返回空列表";
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试另一组包含无效字符的字符串（typeTime）
     */
    @Test
    void testFindShortestPathWithSome_invalidString2_time() {
        System.out.println("测试另一组包含无效字符的字符串（typeTime）");
        List<Integer> list = nodeNavigate.findShortestPathWithSome("startXX endYY", "time");
        assert list != null && list.isEmpty() : "非法输入应返回空列表";
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试空字符串输入（typeTime）
     */
    @Test
    void testFindShortestPathWithSome_emptyString_time() {
        System.out.println("测试空字符串输入（typeTime）");
        List<Integer> list = nodeNavigate.findShortestPathWithSome("", "time");
        assert list != null && list.isEmpty() : "空输入应返回空列表";
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试混合有效和无效字符串（typeTime）
     */
    @Test
    void testFindShortestPathWithSome_mixedInvalidAndValid_time() {
        System.out.println("测试混合有效和无效字符串（typeTime）");
        List<Integer> list = nodeNavigate.findShortestPathWithSome("start01 endYY", "time");
        assert list != null && list.isEmpty() : "混合输入应返回空列表";
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试单个无效字符串（typeTime）
     */
    @Test
    void testFindShortestPathWithSome_singleInvalidString_time() {
        System.out.println("测试单个无效字符串（typeTime）");
        List<Integer> list = nodeNavigate.findShortestPathWithSome("invalidYY", "time");
        assert list != null && list.isEmpty() : "单个无效输入应返回空列表";
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试长度不足2的字符串（typeTime）
     */
    @Test
    void testFindShortestPathWithSome_shortString_time() {
        System.out.println("测试长度不足2的字符串（typeTime）");
        List<Integer> list = nodeNavigate.findShortestPathWithSome("a b", "time");
        assert list != null && list.isEmpty() : "长度不足的输入应返回空列表";
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试字符串中带有空格（typeTime）
     */
    @Test
    void testFindShortestPathWithSome_stringWithSpaces_time() {
        System.out.println("测试字符串中带有空格（typeTime）");
        List<Integer> list = nodeNavigate.findShortestPathWithSome("start 01 end 02", "time");
        assert list != null && list.isEmpty() : "带空格的输入应返回空列表";
        System.out.println("最短路径为：" + list);
    }

    /**
     * 测试起始和结束节点相同（typeTime）
     */
    @Test
    void testFindShortestPathWithSome_sameStartAndEnd_time() {
        System.out.println("测试起始和结束节点相同（typeTime）");
        List<Integer> list = nodeNavigate.findShortestPathWithSome("start01 start01", "time");
        if (list != null) System.out.println("最短路径为：" + list);
    }


    /**
     * 测试placeTypeAndNodeId字符串为Service19
     */
    @Test
    void testFindRelativeNodeWithLength_Service19() {
        System.out.println("测试placeTypeAndNodeId字符串为Service19");
        List<NodeRelative> nodeRelatives = nodeNavigate.findRelativeNodeWithLength("Service19");
        assertNotNull(nodeRelatives, "结果不应为空");
        assertFalse(nodeRelatives.isEmpty(), "结果不应为空列表");
        nodeRelatives.forEach(System.out::println);
    }

    /**
     * 测试placeTypeAndNodeId字符串为Building05
     */
    @Test
    void testFindRelativeNodeWithLength_Building05() {
        System.out.println("测试placeTypeAndNodeId字符串为Building05");
        List<NodeRelative> nodeRelatives = nodeNavigate.findRelativeNodeWithLength("Building05");
        assertNotNull(nodeRelatives, "结果不应为空");
        assertFalse(nodeRelatives.isEmpty(), "结果不应为空列表");
        nodeRelatives.forEach(System.out::println);
    }

    /**
     * 测试placeTypeAndNodeId字符串为Intersection75
     */
    @Test
    void testFindRelativeNodeWithLength_Intersection75() {
        System.out.println("测试placeTypeAndNodeId字符串为Intersection75");
        List<NodeRelative> nodeRelatives = nodeNavigate.findRelativeNodeWithLength("Intersection75");
        assertNotNull(nodeRelatives, "结果不应为空");
        assertFalse(nodeRelatives.isEmpty(), "结果不应为空列表");
        nodeRelatives.forEach(System.out::println);
    }

    /**
     * 测试placeTypeAndNodeId字符串为未知节点
     */
    @Test
    void testFindRelativeNodeWithLength_UnknownNode() {
        System.out.println("测试placeTypeAndNodeId字符串为未知节点");
        List<NodeRelative> nodeRelatives = nodeNavigate.findRelativeNodeWithLength("Unknown99");
        assertNotNull(nodeRelatives, "结果不应为空");
        nodeRelatives.forEach(System.out::println);
    }


    /**
     * 测试placeType为All
     */
    @Test
    void testSelectNodeRelative_All() {
        List<NodeRelative> nodeRelatives = nodeNavigate.findRelativeNodeWithLength("Building05");
        System.out.println("测试placeType为All");
        List<NodeRelative> result = nodeNavigate.selectNodeRelative(nodeRelatives, "All");
        assertNotNull(result, "结果不应为空");
        assertEquals(nodeRelatives.size(), result.size(), "结果大小应与输入列表相同");
        result.forEach(System.out::println);
    }

    /**
     * 测试placeType为Service
     */
    @Test
    void testSelectNodeRelative_Service() {
        System.out.println("测试placeType为Service");
        List<NodeRelative> nodeRelatives = nodeNavigate.findRelativeNodeWithLength("Building05");
        List<NodeRelative> result = nodeNavigate.selectNodeRelative(nodeRelatives, "Service");
        assertNotNull(result, "结果不应为空");
        result.forEach(node -> assertEquals("Service", node.getPlaceType(), "所有元素的placeType应为Service"));
        result.forEach(System.out::println);
    }

    /**
     * 测试placeType为Building
     */
    @Test
    void testSelectNodeRelative_Building() {
        System.out.println("测试placeType为Building");
        List<NodeRelative> nodeRelatives = nodeNavigate.findRelativeNodeWithLength("Building05");
        List<NodeRelative> result = nodeNavigate.selectNodeRelative(nodeRelatives, "Building");
        assertNotNull(result, "结果不应为空");
        result.forEach(node -> assertEquals("Building", node.getPlaceType(), "所有元素的placeType应为Building"));
        result.forEach(System.out::println);
    }

    /**
     * 测试placeType为Intersection
     */
    @Test
    void testSelectNodeRelative_Intersection() {
        System.out.println("测试placeType为Intersection");
        List<NodeRelative> nodeRelatives = nodeNavigate.findRelativeNodeWithLength("Building05");
        List<NodeRelative> result = nodeNavigate.selectNodeRelative(nodeRelatives, "Intersection");
        assertNotNull(result, "结果不应为空");
        result.forEach(node -> assertEquals("Intersection", node.getPlaceType(), "所有元素的placeType应为Intersection"));
        result.forEach(System.out::println);
    }


}