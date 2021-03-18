package features.articles;

import com.intuit.karate.junit5.Karate;
import features.KarateRunnerBase;

public class ArticlesRunner extends KarateRunnerBase {
    @Karate.Test
    Karate testAll() {
        return Karate.run().relativeTo(getClass());
    }
}
