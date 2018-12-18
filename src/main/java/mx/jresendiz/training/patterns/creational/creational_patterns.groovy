import groovy.transform.ToString


/* Singleton
* Muy costoso hacer un objeto:
*   - Acceso a base de datos
*   - Query que se tarda mucho
*   - Swing muy tardado hacerla
*   - IO, Buffers, Sockets ...
*   >>> No va a cambiar (no debería cambiar) durante el tiempo
*   Pros:
*     - Nos ayuda a aislar la creación de un objeto
*     - Nos ayuda a mantener el mismo objeto durante el ciclo de vida de la aplicación
*   Cons:
*     - Muy díficil hacer testing de este patrón
*     - Propenso a cambios (concurrencia, tiempo ... la vida misma)
* */

//class PoorReadIO {
//    public PoorReadIO() {
//        println "Start the object .... Instance created"
//        Thread.sleep(500)
//        println "It's to heavy!!!!"
//    }
//}
//
//
//
//
//def instance = new PoorReadIO()
//println instance
//
//def instance2 = new PoorReadIO()
//println instance2
//
//assert instance == instance2
//

class PoorSingletonReadIO {
    private static PoorSingletonReadIO instance = null
    private Long currentTime = System.currentTimeMillis()

    private PoorSingletonReadIO() {
        // println "Start the object .... Instance created"
        Thread.sleep(500)
        // println "It's to heavy!!!!"
    }
    // Deadlock x_____x
    public static PoorSingletonReadIO create() {
        if (instance == null) {
            //create
            instance = new PoorSingletonReadIO()
        }
        return instance
    }

    public PoorSingletonReadIO getInstance() {
        return instance
    }

    public PoorSingletonReadIO setInstance() {
        throw new Exception('x______x')
    }
}

def instance = PoorSingletonReadIO.create()
//println instance
def instance2 = PoorSingletonReadIO.create()
//println instance2
assert instance == instance2

/*
* Builder
* Solventar y/o lidiar con objetos con muuuuuuuuuuuchos parametros o bien, diferentes opciones
*  - Eg:
*    - Pizzas(queso, doble queso, salchicha rara, chile, parmesano .........) toneladas de opciones
*    - Configuraciones de sistemas de logging (info, debug, warn, diccionario, archivo ....)
*  Pros:
*    - Fácil testing!
*    - Antipattern -> Constructores con muchos parametros  x______x
*    - Programáticamente construir las cosas -> (DSL[domain-specific-language])
*  Cons:
*    - Ocultas el proceso de construccion
*    - Muchos parametros
*    - Muchas opciones por default
*    - Tiende a complementarse con más patrones de diseño
*  El testing es complejo debido a las combinaciones o inexistente x____x
*
* */

interface Pizza {
    int size = 20
    boolean withCheese = true
}

@ToString
class PizzaWithLotOfOptions implements Pizza {
    int size = 20
    String name = 'default'
    boolean withCheese = true

    public PizzaWithLotOfOptions() {
        println "pizza opc0(default)"
    }

    public PizzaWithLotOfOptions(int size) {
        this.size = size
        println "pizza opc1"
    }

    public PizzaWithLotOfOptions(int size, String name) {
        this.size = size
        this.name = name
        println "pizza opc2"
    }

    public PizzaWithLotOfOptions(int size, String name, boolean withCheese) {
        this.size = size
        this.name = name
        this.withCheese = withCheese
        println "pizza opc3"
    }
    // tons of options ... -> tons of constructors ... -> tons of setters ... -> tons of getters
    // x____x -> sad ....
}

//println(new PizzaWithLotOfOptions())
//println(new PizzaWithLotOfOptions(20))
//println(new PizzaWithLotOfOptions(20, 'pepo'))
//println(new PizzaWithLotOfOptions(20, 'pepo', false))

@ToString
class PizzaWithLotOfOptionsBuilder implements Pizza {
    String name = 'default'
    //
    boolean isBuilt = false

    private PizzaWithLotOfOptions() {
        throw new Exception('x___x, cannot instance this! use the pattern!')
    }

    public static PizzaWithLotOfOptionsBuilder create() {
        return new PizzaWithLotOfOptionsBuilder()
    }

    public static PizzaWithLotOfOptionsBuilder buildDefaultPizza() {
        return new PizzaWithLotOfOptionsBuilder().build()
    }

    public static PizzaWithLotOfOptionsBuilder buildPizzaForRafa() {
        return new PizzaWithLotOfOptionsBuilder().setName('ForRafa').build()
    }

    public PizzaWithLotOfOptionsBuilder setSize(int size) {
        this.size = size
        return this
    }

    public PizzaWithLotOfOptionsBuilder setName(String name) {
        this.name = name
        return this
    }

    public PizzaWithLotOfOptionsBuilder setWithCheese(boolean withCheese) {
        this.withCheese = withCheese
        return this
    }

    public PizzaWithLotOfOptionsBuilder build() {
        isBuilt = true
        return this
    }

    public void setIsBuilt(boolean isBuilt) {
        throw new Exception('x___x, cannot change built status ...')
    }
}


//def pizzaBuilder = PizzaWithLotOfOptionsBuilder.create()
//// spoiler alert ... -> Factory! wuuuuuut
//println(pizzaBuilder
//        .setSize(20)
//        .setWithCheese(false)
//        .setName('with builder')
//        .build())
//println(PizzaWithLotOfOptionsBuilder.buildDefaultPizza())
//println(PizzaWithLotOfOptionsBuilder.buildPizzaForRafa())

/*
* Factory
* Fábrica de construcción de objetos, de diversos tipos, diversas opciones, y diversas razones de ser ...
*  - Eg: Pizzas ... PizzaRafa, PizzaPepo, PizzaAzu ... PizzaLightVeganaEcoFriendlyWithoutGlutten, Calzone
*  - Eg: Autos(Puerta auto) ... austera, vidrios electricos, mirrey ...
*  Pros:
*    - Le hace la vida fácil a quienes usan tu código ... (en general)
*    - Testing es fácil
*    - Altamente extensible ...
*    - Evita tener ifs en los constructores (bytecode, JVM)
*    - Evita tener herencia excesiva (usas interfaces)
*    - Catálogo específico
*  Cons:
*    - Mucha talacha
*    - Oscuro el proceso de creación
*    - Agregar nuevos objetos, puede ser complicado, o requiere adecuación
*    - Requieres que no sea muy cambiante el ecosistema
* */

// new pizza1()
// new pizzaForRafa ...
// new pizzaForAzucena ...
// ...

public enum TypeOfPizza {
    RAFA, AZU, PEPO, TONO, CALZONE, CRUDIVEGANAECOFRIENDLY, CUSTOM, UNIQUE
}

class PizzaFactory {
   public static Pizza create(TypeOfPizza type) {
       switch (type) {
           case TypeOfPizza.AZU:
               PizzaWithLotOfOptionsBuilder.create().setName('Azu').build()
               break;
           case TypeOfPizza.RAFA:
               PizzaWithLotOfOptionsBuilder.buildPizzaForRafa()
               break;
           case TypeOfPizza.TONO:
               PizzaWithLotOfOptionsBuilder.create().setName('Tono').build()
               break;
           case TypeOfPizza.CALZONE:
               PizzaWithLotOfOptionsBuilder.create().setSize(10).setName('Calzone').setWithCheese(false).build()
               break;
//           case TypeOfPizza.CUSTOM:
//               PizzaWithLotOfOptionsBuilder.create()
//               break;
//           case TypeOfPizza.UNIQUE:
//               // singleton
//               break;
           default:
               PizzaWithLotOfOptionsBuilder.buildDefaultPizza()
               break;
       }
   }
}

// mock(PizzaFactory).expect(:create).with(TypeOfPizza.AZU).returns((Pizza)ATestPizzaObject)


//println(PizzaFactory.create(TypeOfPizza.AZU))
//println(PizzaFactory.create(TypeOfPizza.RAFA))
//println(PizzaFactory.create(TypeOfPizza.TONO))
//println(PizzaFactory.create(TypeOfPizza.CRUDIVEGANAECOFRIENDLY))
//println(PizzaFactory.create(TypeOfPizza.CUSTOM).setName('CUSTOM').build())
