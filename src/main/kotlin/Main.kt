import de.unitrier.st.uap.w21.tram.Instruction

internal object Main {
    @JvmStatic
    fun main(argv: Array<String>) {
        // TODO: Create an instance of the abstract machine with respective parameters

        println("Running code from class (program1) ...")
        AbstractMachine(Instruction.program1).run()


    }



}