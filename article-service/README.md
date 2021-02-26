# RealWorld - Article Service

## 实现模式（Implementation Patterns）

### 组件：Service / Model / Repository

- 工序#1（30 min）：Stub Repository，实现 Service 组装 Model
- 工序#2（15 min）：Stub / Mock Repository，实现 Service 抛出自定义 RuntimeException
- 工序#3（30 min）：Fake DB，编写 Migration，增加 Model 的 Spring Data JPA 注解，实现 Repository 对 Model 进行持久化操作，填充审计字段。
- 工序#4（5 min）：对 Service 的方法增加 @Transactional 注解实现事务

### 组件：Controller / Request

- 工序#5（30 min）：Fake Http Request，Stub Service，在 Controller 实现指定 API（HttpMethod + URI）和响应结果（Status Code, Response
  Body）
- 工序#6（15 min）：Fake Http Request，Stub Service 抛出异常，在 Controller 实现相应 API 的异常处理，抛出 ResponseStatusException 以返回对应的异常（Status Code、Message）

### API 功能测试

- 工序#7（15 min）：添加 API 功能测试。

## 任务分解（Tasking）

### Story#1：作为作者，我想要创建文章（Article），以便将文章发布到线上，并能通过 slug 得到良好的 SEO。

#### AC#1：当文章创建成功后，需要能够获得根据 title 自动生成的含 slug 的文章地址，以及文章信息。

#### Example#1：成功创建

1. 工序#1（30 min）：Stub ArticleRepository，实现 ArticleService 组装 Article Model（拥有 slug、title、description、body、authorId、createdAt、updatedAt，其中 createdAt、updatedAt 无需赋值），基于 title 生成 slug。当 ArticleRepository 传入组装的 Article 后返回 Stub Article。
2. 工序#4（5 min）：对上一步实现的 ArticleService 方法增加 @Transactional 注解实现事务。
3. 工序#3（30 min）：Fake DB，编写 Migration 创建 article 表，增加 Article 的 Spring Data JPA 注解，实现 ArticleRepository 创建 Article，并自动填充审计字段（createdAt、updatedAt）。
4. 工序#5（30 min）：Stub ArticleService 返回 Stub Article。Fake Http Request 发送请求 `GET /articles { title, description, body, authorId }`。实现 ArticleController，验证响应为 `201 Created { slug、title、description、body、createdAt、updatedAt }`。
5. 工序#7（15 min）：对上一步实现的 API 添加功能测试。

### AC#2: 当存在相同 slug 的文章时，不可创建文章，并提示：`the article with slug {slug} already exists`

#### Example#1：相同 slug 已存在

1. 工序#2（15 min）：Stub ArticleRepository，返回 slug 已存在，实现 ArticleService 抛出 ArticleExistedException，message 为 `the article with slug {slug} already exists`，Mock ArticleRepository 验证未执行 save。
2. 工序#6（15 min）：Stub ArticleService 抛出 ArticleExistedException。Fake Http Request 发送请求 `GET /articles { title, description, body, authorId }`。实现 ArticleController 捕获并抛出 ResponseStatusException。验证响应为 `409 Conflict`，Body 中的 message 为 `the article with slug {slug} already exists`。
3. 工序#7（15 min）：对上一步实现的 API 添加功能测试。
