import React from 'react'
import { Navbar,Container } from 'react-bootstrap'

const NavBar = () => {
  return (
    <>

        <NavBar bg="dark" fixed="top">
            
            <Container>
                
                <NavBar.Brand href="/"><img src='./assets/logo.png' className='logo'/></NavBar.Brand>

            </Container>

        </NavBar>


    </>
  )
}

export default NavBar
