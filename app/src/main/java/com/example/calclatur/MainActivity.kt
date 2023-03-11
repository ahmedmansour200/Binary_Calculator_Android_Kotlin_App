package com.example.calclatur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var editTextDec :EditText
    lateinit var editTextBin :EditText
    lateinit var editTextHix :EditText
    lateinit var editTextOct :EditText
    lateinit var buttonClear:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextDec = findViewById(R.id.editText_dec)
        editTextBin = findViewById(R.id.editText_bin)
        editTextHix = findViewById(R.id.editText_hix)
        editTextOct = findViewById(R.id.editText_oct)
        buttonClear = findViewById(R.id.button_clear)

    buttonClear.setOnClickListener(object: View.OnClickListener{
        override fun onClick(p0: View?) {
            editTextDec.setText("")
            editTextBin.setText("")
            editTextHix.setText("")
            editTextOct.setText("")
        }

    }  )

        editTextDec.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(editTextDec.hasFocus()){
                    fromDecimal(editTextDec.text.toString())
                }
            }
            override fun afterTextChanged(p0: Editable?) {

            }

        } )
        editTextBin.addTextChangedListener(object: TextWatcher{

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if(editTextBin.hasFocus()){

                    fromBinary(editTextBin.text.toString())
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }

        })
        editTextHix.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(editTextHix.hasFocus()){
                    fromHexadecimal(editTextHix.text.toString())
                }
            }
            override fun afterTextChanged(p0: Editable?) {

            }

        } )
        editTextOct.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(editTextOct.hasFocus()){
                    fromOctal(editTextOct.text.toString())
                }
            }
            override fun afterTextChanged(p0: Editable?) {

            }

        } )

    }
    fun fromBinary(binaryNumber: String) {
        if (binaryNumber != "") {
            editTextDec.setText(binaryNumber.toInt(2).toString())
            editTextHix.setText(Integer.toHexString(binaryNumber.toInt(2)))
            editTextOct.setText(convertBinarytoOctal(binaryNumber.toLong()).toString())
        } else {
            editTextHix.setText("")
            editTextDec.setText("")
            editTextOct.setText("")
        }
    }

    fun fromDecimal(decimalNumber: String) {
        if (decimalNumber != "") {
            editTextBin.setText(decimalNumber.toInt().toString(2))
            editTextHix.setText(Integer.toHexString(decimalNumber.toInt()))
            editTextOct.setText(
                convertBinarytoOctal(
                    decimalNumber.toInt().toString(2).toLong()
                ).toString()
            )
        } else {

            editTextHix.setText("")
            editTextBin.setText("")
            editTextOct.setText("")
        }
    }

    fun fromHexadecimal(hexNumber: String) {
        if (hexNumber != "") {
            editTextBin.setText(hexNumber.toLong(16).toString().toInt().toString(2))
            editTextDec.setText(hexNumber.toLong(16).toString())
            editTextOct.setText(
                convertBinarytoOctal(hexNumber.toLong(16).toString().toInt().toString(2).toLong())
                    .toString()
            )
        } else {
            editTextDec.setText("")
            editTextBin.setText("")
            editTextOct.setText("")
        }
    }

    fun fromOctal(octalNumber: String) {
        if (octalNumber != "") {
            editTextBin.setText(convertOctalToBinary(octalNumber.toInt()).toString())
            editTextDec.setText(
                convertOctalToBinary(octalNumber.toInt()).toString().toInt(2).toString()
            )
            editTextHix.setText(
                Integer.toHexString(
                    convertOctalToBinary(octalNumber.toInt()).toString().toInt(2)
                )
            )
        } else {
            editTextDec.setText("")
            editTextBin.setText("")
            editTextHix.setText("")
        }
    }
    fun convertBinarytoOctal(binaryNumber: Long): Int {
        var binaryNumber = binaryNumber
        var octalNumber = 0
        var decimalNumber = 0
        var i = 0

        while (binaryNumber.toInt() != 0) {
            decimalNumber += (binaryNumber % 10 * Math.pow(2.0, i.toDouble())).toInt()
            ++i
            binaryNumber /= 10
        }

        i = 1

        while (decimalNumber != 0) {
            octalNumber += decimalNumber % 8 * i
            decimalNumber /= 8
            i *= 10
        }

        return octalNumber
    }

    fun convertOctalToBinary(octalNumber: Int): Long {
        var octalNumber = octalNumber
        var decimalNumber = 0
        var i = 0
        var binaryNumber: Long = 0

        while (octalNumber != 0) {
            decimalNumber += (octalNumber % 10 * Math.pow(8.0, i.toDouble())).toInt()
            ++i
            octalNumber /= 10
        }

        i = 1

        while (decimalNumber != 0) {
            binaryNumber += (decimalNumber % 2 * i).toLong()
            decimalNumber /= 2
            i *= 10
        }

        return binaryNumber
    }

}