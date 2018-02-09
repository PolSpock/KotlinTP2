package services

import android.os.AsyncTask
import fr.garrycity.pol.kotlintp2.views.EmptyActivity
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import java.net.HttpURLConnection
import java.net.URL
import java.util.logging.Level

/**
 * Created by Pol on 02/02/2018.
 */

class GetRequestService(mContext: EmptyActivity) : AsyncTask<String, String, String>() {
    // Service permettant de faire la requête qui renvoie la réponse via une interface à implémenter
    private val mContext: IGetRequestService

    init {
        this.mContext = mContext;
    }

    override fun doInBackground(vararg params: String?): String {
        var urlConnection: HttpURLConnection? = null
        try {
            val url = URL(params[0])
            urlConnection = url.openConnection() as HttpURLConnection
            return readBuffer(urlConnection.inputStream);
        } catch (ex: Exception) {
            Loggable.LOG.log(Level.SEVERE, "GetRequestService/doInBackground : " + ex)
            return ""
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect()
            }
        }
    }

    override fun onPostExecute(result: String?) {
        mContext.onTaskResult(result)
    }

    private fun readBuffer(inputStream: InputStream): String {
        val bufferReader = BufferedReader(InputStreamReader(inputStream) as Reader?)
        var line: String
        var result = ""
        try {
            do {
                line = bufferReader.readLine()
                if (line != null) {
                    result += line
                }
            } while (line != null)
            inputStream.close()
        } catch (ex: Exception) {
            Loggable.LOG.log(Level.SEVERE, "GetRequestService/readBuffer : " + ex)
        }
        return result
    }

}