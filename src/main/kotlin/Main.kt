import de.unitrier.st.uap.w21.tram.Instruction
import filereader.FileReader

internal object Main {
    @JvmStatic
    fun main(argv: Array<String>) {
        val file = "data/program1.tram"
        // TODO: Create an instance of the abstract machine with respective parameters

        println("Running code from class (program1) ... \n")
        AbstractMachine(Instruction.program1).run()
        println("\n\n")

        println("Running code from file data/program1.tram ...\n")
        val program1 = FileReader().getInstructions("data/program1.tram")
        AbstractMachine(program1).run()
        println("\n\n")

        println("Running code from command line arguments...\n")

        println("\n\n")

    }



}