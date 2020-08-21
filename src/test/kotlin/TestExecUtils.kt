import com.cubetiqs.demo.axon.util.ExecUtils
import org.junit.jupiter.api.Test

class TestExecUtils {
    @Test
    fun dump() {
        val dump = ExecUtils.execMySqlDump()
        println("Dump size: ${dump?.size}")
    }
}