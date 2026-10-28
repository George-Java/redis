# redis

Spring Boot 3.5.16 / JDK 21 的 Redis 学习工程，采用 Maven 多模块（`pom` 聚合）结构。

## 模块

| 模块 | 说明 | 打包 |
| --- | --- | --- |
| `redis`（根工程） | 父工程，只负责聚合子模块、统一 JDK 与依赖版本，不产出可执行包 | `pom` |
| `redis-start` | 单机 Redis 入门：`StringRedisTemplate` + 手动 JSON 序列化 | `jar`（Spring Boot 可执行包） |
| `redis-cluster` | 分片集群 / 哨兵：`HelloController` 读写 Redis，Lettuce 优先读从节点 | `jar`（Spring Boot 可执行包） |

## 环境

- JDK 21（`java.version` / `maven.compiler.release` 由父工程统一指定）
- Spring Boot 3.5.16（父工程继承 `spring-boot-starter-parent`，BOM 统一管理依赖版本）
- 所有模块配置文件均为 `application.yaml`

## 构建与运行

```bash
# 在根工程执行，一次性构建全部模块
./mvnw clean package -DskipTests

# 只构建某个模块
./mvnw -pl redis-start -am clean package

# 运行（测试需本地可用的 Redis 服务）
./mvnw -pl redis-cluster spring-boot:run
```

> `redis-start`、`redis-cluster` 的测试会连接真实 Redis（地址见各模块 `application.yaml`），无可用服务时请跳过测试。

## 目录

```
redis
├── pom.xml                # 父工程：modules + java.version 21 + 插件统一配置
├── redis-start            # 单机示例
└── redis-cluster          # 集群示例
```
