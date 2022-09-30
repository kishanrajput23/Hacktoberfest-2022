import React from 'react'
import styled from 'styled-components'

const Button = ({ text }) => {
    return <Btn className="animated__button">{text}</Btn>
}

const Btn = styled.button`
    outline:none;
    border:2px solid white;
    padding:15px 25px;
    width:150px;
    color:white;
    background:transparent;
    text-transform:uppercase;
    cursor:pointer;
    text-decoration:none;
    position:relative;
    overflow:hidden;
    z-index:100;
    transition:all 0.5s ease-in;

    &:hover{
        background:white;
        color:black;
    }

`

const BannerItem = ({ title, heading, btnText }) => {
    return (
        <ItemText>
            <h3>{title}</h3>
            <h1>{heading}</h1>
            <Button text={btnText} />
        </ItemText>
    )
}

export default BannerItem

const ItemText = styled.div`
    width:50%;
    max-width:520px;
    position:absolute;
    bottom:150px;
    left:100px;
    color:white;
    p{
        font-size:20px;
        font-weight:300;
    }
    h1{
        font-size:40px;
        font-weight:700;
        margin-top:10px;
        margin-bottom:15px;
    }
    @media (max-width:600px){
        width:70%;
        max-width:400px;
        bottom:100px;
        left:50px;
            p{
        font-size:16px;
    }
    h1{
        font-size:30px;
    }
    }
`