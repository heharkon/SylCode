package sylcode

import org.scalatest._

class SylCodeTest extends FlatSpec with Matchers {
  "SylCode" must "always be decodeable" in {

    for (i <- -10000 to 70000) {
      val coded = SylCode.bigIntToSyllableString(i)
      val decoded = SylCode.syllableStringToBigInt(coded).get
      assert(decoded === i)
    }
  }

  "Invalid sylcodes in decode" must "return None" in {
    assert(SylCode.syllableStringToBigInt("ttt").isEmpty)
    assert(SylCode.syllableStringToBigInt("").isEmpty)
  }

  "Encoded id" must "not have collisions" in {
    for (i <- 0 to 255) {
      for ( j <- 0 to 255) {
        val coded = SylCode.bigIntToSyllableString(i)
        if (i != j) {
          val another_coded = SylCode.bigIntToSyllableString(j)
          assert(coded != another_coded)
        }
      }
    }
  }

}
