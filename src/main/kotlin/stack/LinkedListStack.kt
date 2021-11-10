package stack

class LinkedListStack : IStack<Int> {

    private val stack = mutableListOf<Int>()

    override operator fun get(index: Int): Int {
        return if (index < stack.size) {
            stack[index]
        } else {
            //list too short
            stack.add(index, 0)
            0
        }
    }

    override operator fun set(index: Int, value: Int) {
        if (index < stack.size) {
            stack[index] = value
        } else {
            //list to short
            stack.add(0)
            this[index] = value
        }
    }

    fun pop() = stack.removeAt(stack.last())

    override fun toString(): String {
        return stack.toString()
    }

}