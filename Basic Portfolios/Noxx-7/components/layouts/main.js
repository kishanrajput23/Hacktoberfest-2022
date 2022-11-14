import Head from 'next/head'
import dynamic from 'next/dynamic'
import NavBar from '../navbar'
import { Box, Container } from '@chakra-ui/react'
import Footer from '../footer'
import VoxelDogLoader from '../voxel-dog-loader'

const LazyVoxelDog = dynamic(() => import('../voxel-dog'), {
  ssr: false,
  loading: () => <VoxelDogLoader />
})

const Main = ({ children, router }) => {
  return (
    <Box as="main" pb={8}>
      <Head>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="description" content="Rahul's homepage" />
        <meta name="author" content="Rahul Sharma" />
        <meta name="author" content="nox" />
        <meta name="twitter:title" content="Rahul Sharma" />
        <meta name="twitter:card" content="summary_large_image" />
        <meta name="twitter:site" content="@rahuldcrm" />
        <meta name="twitter:creator" content="@rahuldcrm" />
        <meta property="og:site_name" content="Rahul Sharma" />
        <meta name="og:title" content="Rahul Sharma" />
        <meta property="og:type" content="website" />
        <title>Rahul Sharma - Homepage</title>
      </Head>

      <NavBar path={router.asPath} />

      <Container maxW="container.md" pt={14}>
        <LazyVoxelDog />

        {children}

        <Footer />
      </Container>
    </Box>
  )
}

export default Main
