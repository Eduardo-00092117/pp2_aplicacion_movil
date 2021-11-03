package com.proceedto15.wb.database.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cita")
data class Cita(
    @PrimaryKey
    val id: Int = -1,
    val idCliente: Int = -1,
    val fecha: String? = "",
    val hora: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(idCliente)
        parcel.writeString(fecha)
        parcel.writeString(hora)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cita> {
        override fun createFromParcel(parcel: Parcel): Cita {
            return Cita(parcel)
        }

        override fun newArray(size: Int): Array<Cita?> {
            return arrayOfNulls(size)
        }
    }
}