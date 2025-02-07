# Gatling 性能测试项目

## 项目概述

这是一个使用Gatling框架实现的性能测试项目，主要用于测试JSONPlaceholder API的性能表现。项目使用Maven进行构建和管理，采用Scala语言编写测试脚本。

## 环境要求

- Java 11或更高版本
- Maven 3.6.x或更高版本
- Scala 2.13.10
- Gatling 3.9.5

## 项目结构

```
gatling-test/
├── src/
│   └── test/
│       └── scala/
│           └── simulations/
│               └── JsonPlaceholderSimulation.scala
├── pom.xml
└── README.md
```

## 测试场景说明

当前实现的测试场景包括对JSONPlaceholder API的以下操作：

1. 获取所有帖子列表 (GET /posts)
2. 获取单个帖子详情 (GET /posts/1)
3. 获取帖子评论 (GET /posts/1/comments)
4. 创建新帖子 (POST /posts)

### 性能测试配置

- 负载模式：
  - 阶段1：在30秒内逐步增加到50个并发用户
  - 阶段2：保持每秒2个并发用户，持续30秒

- 性能断言：
  - 最大响应时间不超过5000毫秒
  - 成功请求率大于95%

## 如何运行测试

1. 克隆项目到本地：
```bash
git clone <repository-url>
cd gatling-test
```

2. 使用Maven运行测试：
```bash
mvn gatling:test
```

测试完成后，可以在`target/gatling`目录下找到详细的测试报告。

## 测试报告

执行测试后，Gatling会自动生成HTML格式的测试报告，包含以下关键信息：

- 请求响应时间统计
- 并发用户数变化曲线
- 请求成功/失败率
- 各类请求的详细性能指标

报告位置：`target/gatling/[simulation-name-timestamp]/index.html`

## 自定义配置

如需修改测试参数，可以编辑`JsonPlaceholderSimulation.scala`文件：

- 修改并发用户数和持续时间
- 调整请求之间的暂停时间
- 更改性能断言阈值
- 添加新的测试场景

## 注意事项

1. 确保运行测试前有稳定的网络连接
2. 测试报告会占用磁盘空间，可定期清理
3. 建议在非生产环境进行测试

## 依赖版本

- gatling-charts-highcharts: 3.9.5
- gatling-maven-plugin: 4.3.0
- scala-maven-plugin: 4.8.1