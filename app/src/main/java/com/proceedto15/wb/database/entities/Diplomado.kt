package com.proceedto15.wb.database.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diplomado")
data class Diplomado (
    @PrimaryKey
    val id: Int,
    val idImpartidor: Int,
    val nombre: String?,
    val lugar: String?,
    val cupo: Int,
    val fecha_inicio: String?,
    val fecha_fin: String?,
    val duracion: String?,
    val precio: Float,
    val descripcion: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(idImpartidor)
        parcel.writeString(nombre)
        parcel.writeString(lugar)
        parcel.writeInt(cupo)
        parcel.writeString(fecha_inicio)
        parcel.writeString(fecha_fin)
        parcel.writeString(duracion)
        parcel.writeFloat(precio)
        parcel.writeString(descripcion)
    }
    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Diplomado> {
        override fun createFromParcel(parcel: Parcel): Diplomado {
            return Diplomado(parcel)
        }

        override fun newArray(size: Int): Array<Diplomado?> {
            return arrayOfNulls(size)
        }
    }
}