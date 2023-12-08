package example.bagel;

import java.util.List;
import java.util.Map;

class BagelClass implements BagelCallable {
  final String name;
  final BagelClass superclass;
  private final Map<String, BagelFunction> methods;

  BagelClass(String name, BagelClass superclass, Map<String, BagelFunction> methods) {
    this.superclass = superclass;
    this.name = name;
    this.methods = methods;
  }

  BagelFunction findMethod(String name) {
    if (methods.containsKey(name)) {
      return methods.get(name);
    }

    if (superclass != null) {
      return superclass.findMethod(name);
    }

    return null;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public Object call(Interpreter interpreter, List<Object> arguments) {
    BagelInstance instance = new BagelInstance(this);

    BagelFunction initializer = findMethod("init");
    if (initializer != null) {
      initializer.bind(instance).call(interpreter, arguments);
    }

    return instance;
  }

  @Override
  public int arity() {

    BagelFunction initializer = findMethod("init");
    if (initializer == null)
      return 0;
    return initializer.arity();
  }

}
