package model

class Language (var id: String, var name: String) {

    override fun equals(other: Any?)
            = (other is Language)
            && id == other.id
            && name == other.name

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }
}