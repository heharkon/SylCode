package sylcode

import org.scalatest._

class SylCodeTest extends FlatSpec with Matchers {
  "SylCode" should "always be decodeable" in {

    for (i <- -10000 to 70000) {
      val coded = SylCode.bigIntToSyllableString(i)
      val decoded = SylCode.syllableStringToBigInt(coded).get
      assert(decoded === i)
    }
  }

  "Invalid sylcodes in decode" should "return None" in {
    assert(SylCode.syllableStringToBigInt("ttt").isEmpty)
    assert(SylCode.syllableStringToBigInt("").isEmpty)
  }

}
