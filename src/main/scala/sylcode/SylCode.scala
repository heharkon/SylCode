package sylcode

import scala.util.{Success, Try}

object SylCode {
  private val letters = 'a' to 'z'
  private val vowels = List('a', 'e', 'i', 'o', 'u', 'y')
  private val double_vowels = "ai" :: vowels.map(c => c.toString + c.toString)
  private val consonants = (letters.toSet diff vowels.toSet).toList
  private val all_vowels = vowels ++ double_vowels
  private val syllables: List[String] = for (x <- consonants; y <- all_vowels) yield s"$x$y"

  def bigIntToSyllableString(input: BigInt): String = {

    def byteToSyllable(input: Byte): String = {
      val index = input & 0xff
      syllables(index)
    }

    input.toByteArray.map(c => byteToSyllable(c)).mkString("")
  }

  def syllableStringToBigInt(input: String): Option[BigInt] = {

    def charsToByte(chars: List[Char]): Byte = {
      val s = chars.mkString
      syllables.indexOf(s).toByte
    }

    def listToBigIntList(input: List[Char], acc: List[Byte] = List()): List[Byte] = {
      input match {
        case c :: v1 :: v2 :: rest
          if consonants.contains(c) && vowels.contains(v1) && vowels.contains(v2) =>
          listToBigIntList(rest, charsToByte(List(c, v1, v2)) :: acc)

        case c :: v1 :: rest
          if consonants.contains(c) && vowels.contains(v1) =>
          listToBigIntList(rest, charsToByte(List(c, v1)) :: acc)

        case Nil => acc
        case _ => throw new IllegalArgumentException("arg 1 was wrong...")
      }
    }

    Try(listToBigIntList(input.toList)) match {
      case Success(v) if v.nonEmpty => Some(BigInt(v.toArray.reverse))
      case _ => None
    }
  }
}