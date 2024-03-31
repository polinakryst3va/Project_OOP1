import java.util.Set;
import java.util.Map;

public class NFA extends Automaton {
    public NFA(AutomatonBuilder builder) {
        super(builder);
    }


    public static class NFABuilder extends AutomatonBuilder {

        public NFABuilder(int id) {
            super(id);
        }

        @Override
        public NFA build() {
            return new NFA(this);
        }
    }
}
