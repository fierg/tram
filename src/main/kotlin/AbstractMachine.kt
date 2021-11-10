import de.unitrier.st.uap.w21.tram.Instruction
import stack.LinkedListStack
import java.rmi.UnexpectedException

class AbstractMachine(private val program: Array<Instruction>) {
    private val stack = LinkedListStack()
    private var top = 0
    private var pp = 0
    private var fp = 0
    private var pc = 0

    fun run() {
        while (pc >= 0) {
            execute(program[pc])
        }
    }

    private fun execute(instruction: Instruction) {
        println("Instruction: $instruction")

        when (instruction.opcode) {
            Instruction.CONST -> const(instruction)
            Instruction.STORE -> store(instruction)
            Instruction.LOAD -> load(instruction)
            Instruction.ADD -> add(instruction)
            Instruction.MUL -> mul(instruction)
            Instruction.GOTO -> goto(instruction)
            Instruction.IFZERO -> ifZero(instruction)
            Instruction.NOP -> noop()
            Instruction.HALT -> halt()
            else -> throw UnexpectedException("Unexpected instruction: $instruction")
        }
    }

    private fun noop() {
        pc += 1
    }

    private fun ifZero(instruction: Instruction) {
        if (stack.pop() == 0)
            pc = instruction.arg1
        else
            pc += 1

        top -= 1
    }

    private fun goto(instruction: Instruction) {
        pc = instruction.arg1
    }

    private fun halt() {
        pc = -1
    }

    private fun add(instruction: Instruction) {
        stack[top - 1] = stack[top - 1] + stack[top]
        top -=1
        pc += 1
    }

    private fun sub(instruction: Instruction) {
        stack[top - 1] = stack[top - 1] - stack[top]
        top -=1
        pc += 1
    }

    private fun mul(instruction: Instruction) {
        stack[top - 1] = stack[top - 1] * stack[top]
        top -=1
        pc += 1
    }

    private fun div(instruction: Instruction) {
        stack[top - 1] = stack[top - 1] / stack[top]
        top -=1
        pc += 1
    }

    private fun load(instruction: Instruction) {
        stack[top + 1] = stack[pp + instruction.arg1]
        top += 1
        pc += 1
    }

    private fun store(instruction: Instruction) {
        stack[pp + instruction.arg1] = stack[top]
        top -=1
        pc += 1
    }

    private fun const(instruction: Instruction) {
        stack[top + 1] = instruction.arg1
        top += 1
        pc += 1
    }

}