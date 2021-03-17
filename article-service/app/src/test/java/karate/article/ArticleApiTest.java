package karate.article;

import com.intuit.karate.junit5.Karate;
import karate.KarateTestBase;

public class ArticleApiTest extends KarateTestBase {
    @Karate.Test
    Karate testAll() {
        return Karate.run().relativeTo(getClass());
    }
}
