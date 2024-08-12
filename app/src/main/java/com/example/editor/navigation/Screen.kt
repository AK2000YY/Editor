package com.example.editor.navigation

import com.example.editor.core.Constants.COMPETITION_SCREEN
import com.example.editor.core.Constants.FORGOT_PASSWORD_SCREEN
import com.example.editor.core.Constants.HELLO_SCREEN
import com.example.editor.core.Constants.MY_APP_SCREEN
import com.example.editor.core.Constants.PROFILE_SCREEN
import com.example.editor.core.Constants.QUESTION_SCREEN
import com.example.editor.core.Constants.SIGN_IN_SCREEN
import com.example.editor.core.Constants.SIGN_UP_SCREEN
import com.example.editor.core.Constants.TOPIC_SCREEN
import com.example.editor.core.Constants.VERIFY_EMAIL_SCREEN

sealed class Screen(val route:String){
    data object HelloScreen:Screen(HELLO_SCREEN)
    data object SignInScreen:Screen(SIGN_IN_SCREEN)
    data object SignUpScreen:Screen(SIGN_UP_SCREEN)
    data object VerifyEmailScreen:Screen(VERIFY_EMAIL_SCREEN)
    data object ForgotPasswordScreen:Screen(FORGOT_PASSWORD_SCREEN)
    data object MYAPP:Screen(MY_APP_SCREEN)
    data object ProfileScreen:Screen(PROFILE_SCREEN)
    data object CompetitionScreen:Screen(COMPETITION_SCREEN)
    data object QuestionScreen:Screen(QUESTION_SCREEN)
    data object TopicScreen:Screen(TOPIC_SCREEN)
}