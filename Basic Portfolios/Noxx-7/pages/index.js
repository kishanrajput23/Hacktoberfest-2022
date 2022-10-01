import NextLink from 'next/link'
import {
  Link,
  Container,
  Heading,
  Box,

  SimpleGrid,
  Button,
  List,
  ListItem,
  useColorModeValue,
  chakra
} from '@chakra-ui/react'
import { ChevronRightIcon } from '@chakra-ui/icons'
import Paragraph from '../components/paragraph'
import { BioSection, BioYear } from '../components/bio'
import Layout from '../components/layouts/article'
import Section from '../components/section'
import { GridItem } from '../components/grid-item'
import { IoLogoTwitter, IoLogoInstagram, IoLogoGithub } from 'react-icons/io5'
import thumbYouTube from '../public/images/links/youtube.png'
import thumbInkdrop from '../public/images/works/inkdrop_eyecatch.png'
import Image from 'next/image'

const ProfileImage = chakra(Image, {
  shouldForwardProp: prop => ['width', 'height', 'src', 'alt'].includes(prop)
})

const Home = () => (
  <Layout>
    <Container>
      <Box
        borderRadius="lg"
        mb={6}
        p={3}
        textAlign="center"
        bg={useColorModeValue('whiteAlpha.500', 'whiteAlpha.200')}
        css={{ backdropFilter: 'blur(10px)' }}
      >
        👋 Hello, I am a full-stack developer!
      </Box>

      <Box display={{ md: 'flex' }}>
        <Box flexGrow={1}>
          <Heading as="h2" variant="page-title">
            Rahul Sharma
          </Heading>
          <p>Digital Nomad ( Gamer / Developer / Designer )</p>
        </Box>
        <Box
          flexShrink={0}
          mt={{ base: 4, md: 0 }}
          ml={{ md: 6 }}
          textAlign="center"
        >
          <Box
            borderColor="whiteAlpha.800"
            borderWidth={2}
            borderStyle="solid"
            w="100px"
            h="100px"
            display="inline-block"
            borderRadius="full"
            overflow="hidden"
          >
            <ProfileImage
              src="/images/Rahul.jpg"
              alt="Profile image"
              borderRadius="full"
              width="100%"
              height="100%"
            />
          </Box>
        </Box>
      </Box>

      <Section delay={0.1}>
        <Heading as="h3" variant="section-title">
          Namaste
        </Heading>
        <Paragraph>
        I'm a 5th semester student pursuing a Bachelor's in Computer Science with a specialization in Software at CGU Bhubaneswar. I'm a tech enthusiast, web-android developer, freelancer, and part-time YouTuber. In addition to playing around with algorithms and data structures, I'm learning DevOps. I'm looking for a challenging, growth-oriented job where I can apply my technical skills to solve real-life problems in a flexible and dynamic environment. I like to read when I'm not online. I'm learning and contributing to projects right now. You should check this out {' '}
          <NextLink href="/works/inkdrop" passHref scroll={false}>
            <Link>Work</Link>
          </NextLink>
          . I also publishes content on my YouTube
          channel called &quot;
          <NextLink href="https://www.youtube.com/channel/UCS4IkTAy5uw-BRQXFsnt0xw/videos" passHref>
            <Link target="_blank">TechNox</Link>

          </NextLink>
          &quot; and
          <NextLink href="https://www.youtube.com/channel/UCgk3qHyHZKhcIc7U6lp0RHg" passHref>
            <Link target="_blank"> Vlognox</Link>

          </NextLink>
        </Paragraph>
        <Box align="center" my={4}>
          <NextLink href="/works" passHref scroll={false}>
            <Button rightIcon={<ChevronRightIcon />} colorScheme="teal">
              My portfolio
            </Button>
          </NextLink>
        </Box>
      </Section>

      <Section delay={0.2}>
        <Heading as="h3" variant="section-title">
          Bio
        </Heading>
        <BioSection>
          <BioYear>2000</BioYear>
          Born in Odisha, INDIA.
        </BioSection>
        <BioSection>
          <BioYear>2017</BioYear>
          Completed My Matriculation (8.2 CGPA)
        </BioSection>
        <BioSection>
          <BioYear>2019</BioYear>
          Completed My 12th (50%)
        </BioSection>
        <BioSection>
          <BioYear>2020 to present</BioYear>
          pursuing B.Tech in C.V Raman Global University
        </BioSection>
      </Section>

      <Section delay={0.3}>
        <Heading as="h3" variant="section-title">
          I ♥
        </Heading>
        <Paragraph>
          <Link href="https://instagram.com/___sketch7?igshid=YmMyMTA2M2Y=/" target="_blank">
            Drawing {' '}
          </Link>
          and {'  '}
          <Link href="https://www.youtube.com/channel/UCtMSWqLw2pf9dFHDcNkURUQ" target="_blank">
            Gaming
          </Link>

        </Paragraph>
      </Section>

      <Section delay={0.3}>
        <Heading as="h3" variant="section-title">
          Lets Connect
        </Heading>
        <List>
          <ListItem>
            <Link href="https://github.com/Noxx-7" target="_blank">
              <Button
                variant="ghost"
                colorScheme="teal"
                leftIcon={<IoLogoGithub />}
              >
                @Noxx-7

              </Button>
            </Link>
          </ListItem>
          <ListItem>
            <Link href="https://twitter.com/rahuldcrm" target="_blank">
              <Button
                variant="ghost"
                colorScheme="teal"
                leftIcon={<IoLogoTwitter />}
              >
                @rahuldcrm
              </Button>
            </Link>
          </ListItem>
          <ListItem>
            <Link href="https://instagram.com/___nox7?igshid=YmMyMTA2M2Y=" target="_blank">
              <Button
                variant="ghost"
                colorScheme="teal"
                leftIcon={<IoLogoInstagram />}
              >
                @___nox7
              </Button>
            </Link>
          </ListItem>
        </List>


        <SimpleGrid columns={[1, 2, 2]} gap={6}>
          <GridItem
            href="https://www.youtube.com/channel/UCS4IkTAy5uw-BRQXFsnt0xw"
            title="TechNox"
            thumbnail={thumbYouTube}
          >
            My YouTube channel
          </GridItem>
          <GridItem
            href="https://www.linkedin.com/in/rahul-sharma-603935182/"
            title="linkedin"
            thumbnail={thumbInkdrop}
          >
            My linkedin Account
          </GridItem>
        </SimpleGrid>

        <Box align="center" my={4}>
          <NextLink href="/posts" passHref scroll={false}>
            <Button rightIcon={<ChevronRightIcon />} colorScheme="teal">
              Popular posts
            </Button>
          </NextLink>
        </Box>
      </Section>
    </Container>
  </Layout>
)

export default Home
export { getServerSideProps } from '../components/chakra'
