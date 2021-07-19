package ar.com.wolox.android.example.ui.example

import ar.com.wolox.android.example.model.AuthenticationBody
import ar.com.wolox.android.example.model.User
import ar.com.wolox.android.example.network.repository.UserRepository
import ar.com.wolox.android.example.utils.Extras
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.tests.CoroutineTestRule
import ar.com.wolox.wolmo.core.tests.WolmoPresenterTest
import ar.com.wolox.wolmo.networking.retrofit.handler.NetworkResponse
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.Headers
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import retrofit2.Response

@ExperimentalCoroutinesApi
class LoginPresenterTest : WolmoPresenterTest<LoginView, LoginPresenter>() {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule(runOnAllTests = true)

    @Mock
    lateinit var userSession: UserSession

    @Mock
    lateinit var userRepository: UserRepository

    override fun getPresenterInstance() = LoginPresenter(userSession, userRepository)

    @Test
    fun UserAndPaswordEmptyValidation() {
        // Usuario y contraseña vacio
        val username = ""
        val password = ""

        presenter.onLoginButtonClicked(username, password)

        verify(view, times(1)).showError(Extras.UserLogin.USERNAME)
        verify(view, times(1)).showError(Extras.UserLogin.PASSWORD)
    }

    @Test
    fun UserIcorrectAndPaswordEmptyValidation() {
        // Usuario invalido y contraseña vacia
        val username = "prueba"
        val password = ""

        presenter.onLoginButtonClicked(username, password)

        verify(view, times(1)).showError(Extras.UserLogin.VALID_EMAIL)
        verify(view, times(1)).showError(Extras.UserLogin.PASSWORD)
    }

    @Test
    fun UserCorrectAndPaswordEmtpyValidation() {
        // Usuario correcto y contraseña vacia
        val username = "prueba@prueba.com"
        val password = ""

        presenter.onLoginButtonClicked(username, password)

        verify(view, times(1)).showError(Extras.UserLogin.PASSWORD)
    }

    @Test
    fun requestErrorLogin() = runBlocking {

        // Usuario y contraseña incorrectas
        val username = "prueba@prueba.com"
        val password = "123"

        // Usuario response
        val authenticationbody = AuthenticationBody(username, password)

        val response = Mockito.mock(Response::class.java) as Response<User>
        whenever(response.code()).doReturn(401)
        whenever(userRepository.signIn(authenticationbody)).doReturn(NetworkResponse.Error(response))

        presenter.loginNetwork(username, password).join()

        verify(view, times(1)).showError(Extras.Constantes.ERROR_USER_PASS)
    }

    @Test
    fun requestFailedLogin() = runBlocking {

        // Usuario y contraseña correctas pero hay error de conexion a la red
        val username = "melvin.lambert15@example.com"
        val password = "123456"

        // Usuario response
        val authenticationbody = AuthenticationBody(username, password)

        whenever(userRepository.signIn(authenticationbody)).doReturn(NetworkResponse.Failure(Throwable()))

        presenter.loginNetwork(username, password).join()

        verify(view, times(1)).showError(Extras.Constantes.ERROR_NETWORK)
    }

    @Test
    fun requestOkLogin() = runBlocking {

        // Usuario y contraseña correctas
        val username = "melvin.lambert15@example.com"
        val password = "123456"

        // Body Response
        val id = 6
        val email = "melvin.lambert15@example.com"
        val provider = "email"
        val uid = "melvin.lambert15@example.com"
        val allow_password_change = false
        val name = "Melvin Lambert"
        val nickname = "Melvin"
        val image = ""
        val user = User(id, email, provider, uid, allow_password_change, name, nickname, image)

        // Header Response
        val header_accessToken = "I54n43S_l62NDKWXbuZaSA"
        val header_client = "P-DyyGXKtHWrc1Lg5wnY2g"
        val header_uid = "melvin.lambert15@example.com"

        // Usuario response
        val authenticationbody = AuthenticationBody(username, password)

        val response = Mockito.mock(Response::class.java) as Response<User>
        val headers = Headers.Builder()
        headers.add("Access-Token", header_accessToken)
        headers.add("Client", header_client)
        headers.add("Uid", header_uid)
        whenever(response.body()).doReturn(user)
        whenever(response.headers()).doReturn(headers.build())
        whenever(userRepository.signIn(authenticationbody)).doReturn(NetworkResponse.Success(response))

        presenter.loginNetwork(username, password).join()

        verify(userSession, times(1)).loginOk = true
        verify(userSession, times(1)).username = username
        verify(userSession, times(1)).password = password
        verify(userSession, times(1)).acces_token = header_accessToken
        verify(userSession, times(1)).uid = header_uid
        verify(userSession, times(1)).client = header_client

        verify(view, times(1)).showHome()
    }
}