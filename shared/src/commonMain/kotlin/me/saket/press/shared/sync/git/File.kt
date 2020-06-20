package me.saket.press.shared.sync.git

expect class PlatformFile(parentPath: String, name: String) : File {
  constructor(path: String)
}

interface File {
  companion object {
    // So that usages can use File() directly instead of PlatformFile().
    operator fun invoke(path: String): File = PlatformFile(path)
    operator fun invoke(parent: File, name: String): File = PlatformFile(parent.path, name)
    operator fun invoke(parentPath: String, name: String): File = PlatformFile(parentPath, name)
  }

  val exists: Boolean
  val path: String
  val name: String
  val parent: File?

  val extension: String
    get() = name.substringAfterLast('.', "")

  val nameWithoutExtension: String
    get() = name.substringBeforeLast('.')

  fun write(input: String)

  fun read(): String

  fun copy(name: String, recursively: Boolean = false): File

  fun makeDirectory(recursively: Boolean = false)

  fun delete(recursively: Boolean = false)

  fun children(): List<File>
}