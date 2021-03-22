// Karate 配置文件
function fn() {

    // 通过系统属性获取 SpringBootTest 注入的随机端口（需配合 KarateRunnerBase 中的设置代码）
    const port = karate.properties['local.server.port'];

    // 设置 Karate 配置的全局变量供 feature 文件调用
    const config = {
        localUrl: 'http://localhost:' + port,
        dbRestoreFeature: 'classpath:features/db-restore.feature'
    };

    // 设置连接和读取超时时间
    karate.configure('connectTimeout', 5000);
    karate.configure('readTimeout', 5000);

    return config;
}
