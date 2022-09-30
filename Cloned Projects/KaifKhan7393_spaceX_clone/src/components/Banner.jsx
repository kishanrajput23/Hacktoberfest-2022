import React from 'react'
import styled from 'styled-components'
import BannerItem from './BannerItem';

const Banner = ({ bgImage, title, heading, btnText }) => {
    return (
        <Container bg={bgImage}>
            <BannerItem title={title} heading={heading} btnText={btnText} />
        </Container>
    )
}

const Container = styled.div`
    width:100%;
    height:100vh;
    background-image:url(${props => props.bg});
    background-size: cover;
    background-repeat: no-repeat;
    background-position:center;
    position:relative;
    z-index:1;
`

export default Banner