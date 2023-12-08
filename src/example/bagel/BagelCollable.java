package example.bagel;

import java.util.List;

interface BagelCallable {
  int arity();

  Object call(Interpreter interpreter, List<Object> arguments);
}
