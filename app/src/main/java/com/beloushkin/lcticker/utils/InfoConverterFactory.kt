package com.beloushkin.lcticker.utils

import com.beloushkin.lcticker.data.models.Info
import com.beloushkin.lcticker.data.services.HasRoot_Info
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type


class InfoConverterFactory: Converter.Factory() {

    override fun responseBodyConverter(
        type: Type?,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {

        val envelopedType = TypeToken.getParameterized(Info::class.java, type).type
        val delegate: Converter<ResponseBody, Any?> =
            retrofit.nextResponseBodyConverter(this, envelopedType, annotations)



        return if(hasInfoAnnotation(annotations))
            //Converter { body: ResponseBody -> body }
            Converter { body: ResponseBody ->
                val info: Info? = delegate.convert(body) as Info
                info?.info
            }
        else
            super.responseBodyConverter(type, annotations, retrofit)
    }

    private fun hasInfoAnnotation(annotations: Array<Annotation>):Boolean {
        for (annotation in annotations) {
            return annotation is HasRoot_Info
        }
        return false
    }

}