import de.unitrier.st.uap.w21.tram.Instruction
import filereader.FileReader
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import org.apache.logging.log4j.kotlin.logger

internal object Main {
    @JvmStatic
    fun main(argv: Array<String>) {
        val logger = logger(Main.javaClass.simpleName)

        val debug = handleCLIArguments(argv)

        if(debug) logger.info("debug mode activated")

        val file = "data/program1.tram"
        // TODO: Create an instance of the abstract machine with respective parameters

        println("Running code from class (program1) ... \n")
        AbstractMachine(Instruction.program1,debug).run()
        println("\n\n")

        println("Running code from file data/program1.tram ...\n")
        val program1 = FileReader().getInstructions("data/program1.tram")
        AbstractMachine(program1,debug).run()
        println("\n\n")

        println("Running code from command line arguments...\n")

        println("\n\n")

    }

    private fun handleCLIArguments(argv: Array<String>): Boolean {
        val parser = ArgParser("tram")
        val debug by parser.option(ArgType.Boolean, shortName = "d", description = "Turn on debug mode").default(false)
        parser.parse(argv)
        return debug
    }


}