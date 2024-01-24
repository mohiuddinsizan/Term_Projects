import React from 'react'
import './Login.css'
import { Link } from 'react-router-dom';
const Login = () => {
    return ( 
        <div className='login'>
            <div className='login-container'>
                <h1>Sign Up</h1>
                <div className='login-fields'>
                    <input type = 'text1' placeholder='Username'></input>
                    <input type = 'email' placeholder='E-mail'></input>
                    <input type = 'password' placeholder='password'></input>
                </div>
                <button>Continue</button>
                <p className='already-account'>Already have an account ?<Link style={{textDecoration:'none'}} to ='/userlogin'><button>Login</button></Link> </p>
                <div className='login-agree'>
                    <input type='checkbox' name = '' id = ''></input>
                    By Continuing,I agree to the terms of use & privacy policy.
                </div>
            </div>
        </div>

     );
}
 
export default Login;
