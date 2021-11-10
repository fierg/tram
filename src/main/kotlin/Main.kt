import de.unitrier.st.uap.w21.tram.Instruction
import filereader.FileReader
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import kotlinx.cli.optional
import org.apache.logging.log4j.kotlin.logger

internal object Main {
    @JvmStatic
    fun main(argv: Array<String>) {
        val logger = logger(Main.javaClass.simpleName)

        val cli = handleCLIArguments(argv)
        val debug = cli.first

        if (debug) logger.info("debug mode activated")

        println("Running code from class (program1) ... \n")
        AbstractMachine(Instruction.program1, debug).run()
        println("\n\n")

        println("Running code from file data/program1.tram ...\n")
        val program1 = FileReader().getInstructions("data/program1.tram")
        AbstractMachine(program1, debug).run()
        println("\n\n")

        val program2 = cli.second
        if (!program2.isNullOrEmpty()) {
            println("Running code from command line arguments...\n")
            AbstractMachine(FileReader().getInstructions(program2), debug).run()
        }
        println("\n\n")

    }

    private fun handleCLIArguments(argv: Array<String>): Pair<Boolean, String?> {
        val parser = ArgParser("tram")
        val debug by parser.option(ArgType.Boolean, shortName = "d", description = "Turn on debug mode").default(false)
        val input by parser.argument(ArgType.String, description = "Input file").optional()
        parser.parse(argv)
        return Pair(debug, input)
    }


}