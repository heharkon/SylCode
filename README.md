# SylCode

SylCode is a small piece of Scala code for presenting ID numbers
as pronounceable "words", combinations of syllables, consisting of
a consonant and one or two vowels.

SylCode is inspired by https://github.com/heharkon/readable-hash project
and others.

## Usage

To convert an ID number to a word:

```
SylCode.bigIntToSyllableString(BigInt(34873925))
```

will give you coded ID `sixaaneefu`.

If there's a need, you can decode it back to BigInt:

```
SylCode.syllableStringToBigInt("sixaaneefu")
```

will give you `Some(BigInt(34873925))` or `None`, if it couldn't
be decoded.
