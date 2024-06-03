package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.dto.NodeInfo;
import com.example.mapper.NodeInfoMapper;
import com.example.service.NodeNavigateService;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.ToDoubleFunction;

@Service
public class NodeNavigateImpl extends ServiceImpl<NodeInfoMapper, NodeInfo> implements NodeNavigateService {

    // 注入NodeInfoMapper
    @Resource
    private NodeInfoMapper nodeInfoMapper;

    // 检查字符串是否为数字
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // 实现findShortestPathWithTwo方法，找到两点间最短路径或最短时间路径
    // 如果输入不合法 返回一个空的list<Integer>
    public List<Integer> findShortestPathWithTwo(String from, String to, String type) {

        // 提取from和to的最后两位作为source和destination，并进行判断
        if (from.length() < 2 || to.length() < 2) {
            return new LinkedList<>();
        }

        String sourceStr = from.substring(from.length() - 2);
        String destinationStr = to.substring(to.length() - 2);

        //输入串不合法，返回list=[]
        if (!isNumeric(sourceStr) || !isNumeric(destinationStr)) {
            return new LinkedList<>();
        }

        // 提取from和to的最后两位作为source和destination
        int source = Integer.parseInt(sourceStr);
        int destination = Integer.parseInt(destinationStr);

        if (source < 0 || source > 100 || destination < 0 || destination > 99) {
            return new LinkedList<>();
        }
        // 查询所有节点信息
        List<NodeInfo> nodeInfos = nodeInfoMapper.selectList(null);

        // 构建图结构
        Map<Integer, List<NodeInfo>> graph = buildGraph(nodeInfos);

        // 根据type选择计算最短路径或最短时间路径
        return type.equals("short") ?
                findShortestPathByLength(graph, source, destination) :
                findShortestPathByTime(graph, source, destination);
    }

    // 构建图结构，使用邻接表表示
    private Map<Integer, List<NodeInfo>> buildGraph(List<NodeInfo> nodeInfos) {
        Map<Integer, List<NodeInfo>> graph = new HashMap<>();
        for (NodeInfo nodeInfo : nodeInfos) {
            graph.computeIfAbsent(nodeInfo.getSource(), k -> new ArrayList<>()).add(nodeInfo);
            graph.computeIfAbsent(nodeInfo.getDestination(), k -> new ArrayList<>()).add(nodeInfo);
        }
        return graph;
    }

    // 计算两点间最短路径（最小长度）
    private List<Integer> findShortestPathByLength(Map<Integer, List<NodeInfo>> graph, int source, int destination) {
        return dijkstra(graph, source, destination, NodeInfo::getLength);
    }

    // 计算两点间最短时间路径
    private List<Integer> findShortestPathByTime(Map<Integer, List<NodeInfo>> graph, int source, int destination) {
        return dijkstra(graph, source, destination, nodeInfo -> nodeInfo.getLength() / (nodeInfo.getSpeed() * nodeInfo.getCongestion()));
    }

    // 使用Dijkstra算法计算最短路径
    private List<Integer> dijkstra(Map<Integer, List<NodeInfo>> graph, int source, int destination, ToDoubleFunction<NodeInfo> weightFunction) {
        // 使用优先队列实现Dijkstra算法
        PriorityQueue<PathNode> queue = new PriorityQueue<>(Comparator.comparingDouble(PathNode::getWeight));
        queue.add(new PathNode(source, 0));
        Map<Integer, Double> distances = new HashMap<>();
        Map<Integer, Integer> previous = new HashMap<>();
        distances.put(source, 0.0);

        while (!queue.isEmpty()) {
            PathNode currentNode = queue.poll();
            int current = currentNode.getNode();

            if (current == destination) {
                return reconstructPath(previous, destination);
            }

            for (NodeInfo neighbor : graph.getOrDefault(current, Collections.emptyList())) {
                int nextNode = neighbor.getDestination() == current ? neighbor.getSource() : neighbor.getDestination();
                double newDist = distances.get(current) + weightFunction.applyAsDouble(neighbor);

                if (newDist < distances.getOrDefault(nextNode, Double.MAX_VALUE)) {
                    distances.put(nextNode, newDist);
                    previous.put(nextNode, current);
                    queue.add(new PathNode(nextNode, newDist));
                }
            }
        }

        return Collections.emptyList(); // 没有找到路径
    }

    // 重建路径，用于返回路径中的每个点
    private List<Integer> reconstructPath(Map<Integer, Integer> previous, int destination) {
        List<Integer> path = new ArrayList<>();
        for (Integer at = destination; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    // PathNode类，封装节点信息和权重，用于优先队列中的节点排序
    @Getter
    private static class PathNode {
        private final int node;
        private final double weight;

        public PathNode(int node, double weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    @Override
    public List<Integer> findShortestPathWithSome(String placeList, String type) {
        // 将输入字符串按空格分隔开
        String[] places = placeList.split(" ");
        List<Integer> nodes = new ArrayList<>();

        // 提取每个字符串的最后两位作为节点，并进行合法性检查
        for (String place : places) {
            if (place.length() < 2) {
                return Collections.emptyList();  // 输入无效，返回空列表
            }
            String lastTwoChars = place.substring(place.length() - 2);
            try {
                int node = Integer.parseInt(lastTwoChars);
                if (node < 0 || node > 99) {
                    return Collections.emptyList();  // 节点无效，返回空列表
                }
                nodes.add(node);
            } catch (NumberFormatException e) {
                return Collections.emptyList();  // 非数字输入，返回空列表
            }
        }

        // 确保路径能够回到起点
        if (nodes.isEmpty()) {
            return Collections.emptyList();  // 节点列表为空，返回空列表
        }
        nodes.add(nodes.get(0));  // 添加起点到末尾，确保回到起点

        // 查询所有节点信息
        List<NodeInfo> nodeInfos = nodeInfoMapper.selectList(null);
        // 构建图结构
        Map<Integer, List<NodeInfo>> graph = buildGraphForSome(nodeInfos);

        // 根据type计算路径
        if (type.equals("short")) {
            return findShortestPathByLengthSome(graph, nodes);
        } else if (type.equals("time")) {
            return findShortestPathByTimeSome(graph, nodes);
        } else {
            return Collections.emptyList(); // 无效的类型，返回空列表
        }
    }


    // 构建图结构，使用邻接表表示
    private Map<Integer, List<NodeInfo>> buildGraphForSome(List<NodeInfo> nodeInfos) {
        Map<Integer, List<NodeInfo>> graph = new HashMap<>();
        for (NodeInfo nodeInfo : nodeInfos) {
            graph.computeIfAbsent(nodeInfo.getSource(), k -> new ArrayList<>()).add(nodeInfo);
            graph.computeIfAbsent(nodeInfo.getDestination(), k -> new ArrayList<>()).add(nodeInfo);
        }
        return graph;
    }

    // 计算经过传入的每个点的最短路径（最小长度）
    private List<Integer> findShortestPathByLengthSome(Map<Integer, List<NodeInfo>> graph, List<Integer> nodes) {
        List<Integer> resultPath = new ArrayList<>();
        for (int i = 0; i < nodes.size() - 1; i++) {
            List<Integer> pathSegment = dijkstra(graph, nodes.get(i), nodes.get(i + 1), NodeInfo::getLength);
            if (pathSegment.isEmpty()) {
                return Collections.emptyList(); // 没有找到路径，返回空列表
            }
            if (i > 0) {
                pathSegment.remove(0); // 移除重复的起始节点
            }
            resultPath.addAll(pathSegment);
        }
        return resultPath;
    }

    // 计算经过传入的每个点的最短时间路径
    private List<Integer> findShortestPathByTimeSome(Map<Integer, List<NodeInfo>> graph, List<Integer> nodes) {
        List<Integer> resultPath = new ArrayList<>();
        for (int i = 0; i < nodes.size() - 1; i++) {
            List<Integer> pathSegment = dijkstra(graph, nodes.get(i), nodes.get(i + 1), nodeInfo -> nodeInfo.getLength() / (nodeInfo.getSpeed() * nodeInfo.getCongestion()));
            if (pathSegment.isEmpty()) {
                return Collections.emptyList(); // 没有找到路径，返回空列表
            }
            if (i > 0) {
                pathSegment.remove(0); // 移除重复的起始节点
            }
            resultPath.addAll(pathSegment);
        }
        return resultPath;
    }
}

