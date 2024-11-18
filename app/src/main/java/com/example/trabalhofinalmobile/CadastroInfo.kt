package com.example.trabalhofinalmobile

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CadastroInfo(
    val descricao: String,
    val valor: Double,
    val tipoPagamento: Char
) : Parcelable
