function fn() {

    var port = karate.properties['local.server.port'];

    var config = {
        localUrl: 'http://localhost:' + port
    };

    karate.configure('connectTimeout', 5000);
    karate.configure('readTimeout', 5000);

    return config;
}
