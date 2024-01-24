import React from 'react'
import './Hero.css'
import logo from '../Assets/logo.png'
const Hero = () => {
    return ( 
        <div className="hero">
            <div className='hero-left'>
                <p>"Welcome To The World's Largest Online Book Shop"</p><br></br><br></br>
            </div>
            <div className='hero-right'>
            <img src={logo} alt = "" />
            </div>
        </div>
     );
}
 
export default Hero;