package mx.jresendiz.training.patterns.behavioral

import groovy.transform.ToString

/*
* Strategy
*
* */

interface SortStrategy {
    void sortArray(List list);
}

class Context {
    SortStrategy context;

    Context(SortStrategy strategy) {
        this.context = strategy
    }
    // facade
    void executeSortStrategy(List list) {
        context.sortArray(list);
    }
}


class SuperSlowSort implements SortStrategy {
    @Override
    void sortArray(List list = []) {
        Thread.sleep(5000)
        println "[SuperSlowSort] ${list.sort()} finished ..."
    }
}

class SuperFastSort implements SortStrategy {
    @Override
    void sortArray(List list = []) {
        Thread.sleep(500)
        println "[SuperFastSort] ${list.sort()} finished ..."
    }
}

//def listToSort = [-1, 2, 3, 5, 6, 7, 8, 11, 1, 12, 3, 5, 7, 8, 00, 1, 886, 0]
//
//SortStrategy slowSort = new SuperSlowSort();
//
//Context context = new Context(slowSort);
//context.executeSortStrategy(listToSort as List);
//
//SortStrategy fastSort = new SuperFastSort();
//
//context.context = fastSort;
//context.executeSortStrategy(listToSort as List);
//
//// Change of scope
//context.context = slowSort;
//context.executeSortStrategy(listToSort as List);

/*
extractor.process('lista')

    Strategy1
    conf['desired-strategy'] = 'API'
    http.post('process/', lista)

    Strategy2
    db.query ...

* */

/*
Memento

* */

interface Memento {
    String getName()
    Date getDate()
}

@ToString
class ConcreteMemento implements Memento {

    String state
    Date date

    ConcreteMemento(String state) {
        this.state = state
        this.date = new Date()
        println "[ConcreteMemento] Creating memento ..."
    }

    @Override
    String getName() {
        return "${date}-${state}"
    }

    @Override
    Date getDate() {
        return this.date
    }

    String getState() {
        this.state
    }
}

class Originator {
    String state

    Originator(String state) {
        println "[Originator] Creating originator ..."
        this.state = state
    }

    void modifyStatus() {
        println "[Originator] Doing some stuff that modifies the state ..."
        state = UUID.randomUUID()
        println "[Originator] State has changed ..."
    }

    Memento save() {
        println "[Originator] Creating new memento with new state ..."
        return new ConcreteMemento(this.state)
    }

    void restore(Memento memento) {
        println "[Originator] Restoring state from memento ... "
        this.state = memento.getState()
    }

    String getState() {
        println "[Originator] current status ... ${state}"
        return state
    }


}


class Caretaker {
    LinkedList<Memento> mementos = new LinkedList<>()
    Originator originator

    Caretaker(Originator originator) {
        this.originator = originator
        println "[Caretaker] Adding originator to Caretaker"
    }

    void backup() {
        println "[Caretaker] Creating a backup from memento ..."
        mementos.add(this.originator.save())
    }

    void undo() {
        if (mementos.empty) {
            return
        }
        Memento memento = mementos.pop()
        println "[Caretaker] Restoring state of memento ..."
        try {
            this.originator.restore(memento)
        } catch (Exception ignored) {
            this.undo()
        }
    }

    void showHistory() {
        println "[Caretaker] List of mementos ..."
        this.mementos.each { memento ->
            println "\t >>> " + memento
        }

    }
}

//Originator originator = new Originator("Initial state ...")
//
//Caretaker caretaker = new Caretaker(originator)
//
//originator.state
//
//caretaker.backup()
//originator.modifyStatus()
//originator.state
//
//caretaker.backup()
//originator.modifyStatus()
//originator.state
//
//caretaker.backup()
//originator.modifyStatus()
//originator.state
//
//caretaker.showHistory()
//
//caretaker.undo()
//originator.state
//
//caretaker.undo()
//originator.state
//
//caretaker.showHistory()
//
//caretaker.undo()
//originator.state
//
//caretaker.showHistory()

/*
Command
* */


interface Command {
    void execute();
}

class SimpleCommand implements Command {

    String action

    SimpleCommand(String action) {
        this.action = action
    }

    @Override
    void execute() {
        println "[SimpleCommand] A simple command to be executed ... ${action}"
    }
}

class ComplexCommand implements Command {
    Receiver receiver
    String action


    ComplexCommand(Receiver receiver, String action) {
        this.receiver = receiver
        this.action = action
    }

    ComplexCommand(String action) {
        this.action = action
    }

    @Override
    void execute() {
        Thread.sleep(500)
        println "[ComplexCommand] A complex command to be executed ... ${this.action}"
        if (receiver) receiver.doSomething();
    }
}

class Receiver {
    void doSomething() {
        println "[Receiver] Something done by the receiver"
    }
}

class Invoker {
    Command onStart
    Command during
    Command onEnd

    void doSomethingImportant() {
        println "[Invoker] Starting doing something important ..."
        if (onStart) onStart.execute();

        println "[Invoker] An action was executed before"

        if (during) during.execute();

        println "[Invoker] An action was executed in the meanwhile"

        if (onEnd) onEnd.execute();

        println "[Invoker] An action was executed at the end ...,"
    }
}


//Invoker invoker = new Invoker()
//Receiver receiver = new Receiver()

//invoker.setOnStart(new SimpleCommand("At the beginning..."))
//invoker.setDuring(new ComplexCommand(receiver, "Complex action to be executed"))
//invoker.setOnEnd(new SimpleCommand("At the end..."))

//invoker.setOnStart(new SimpleCommand("Abrir receta..."))
//invoker.setDuring(new ComplexCommand(receiver, "Chef se rifa el fisico "))
//invoker.setOnEnd(new SimpleCommand("Se le cae el plato"))
//
//invoker.doSomethingImportant()

//
//
// Pool of jobs
List<Command> commands = [] // Message Broker ... (RabbitMQ, ActiveMQ, RocketMQ ...)

(1..10).each {
    Command command = null
    if (new Random().nextBoolean()) {
        command = new SimpleCommand("A simple command to be executed: ${it}")
    } else {
        command = new ComplexCommand("A complex command to be executed: ${it}")
    }
    commands.add(command)
}

for (command in commands) {
    command.execute()
}