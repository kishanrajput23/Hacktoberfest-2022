import {
  Container,
  Badge,
  Link,
  List,
  ListItem,

} from '@chakra-ui/react'
import Layout from '../../components/layouts/article'
import { ExternalLinkIcon } from '@chakra-ui/icons'
import { Title, Meta } from '../../components/work'
import P from '../../components/paragraph'

const Work = () => (
  <Layout title="walknote">
    <Container>
      <Title>
        My First WebSite <Badge>2021-2022</Badge>
      </Title>
      <P>
        This was my first website made with (Html, Css, Javascript)
      </P>
      <P>
        This has been outdated as i am not using this website
      </P>
      <List ml={4} my={4}>
        <ListItem>
          <Meta>Platform</Meta>
          <span>Windows</span>
        </ListItem>
        <ListItem>
          <Meta>Check this</Meta>
          <Link href="https://noxx-7.github.io/Rahul-Sharma.github.io/">
            Website (first) {' '}
            <ExternalLinkIcon mx="2px" />
          </Link>
        </ListItem>
        <ListItem>
          <Meta>Github</Meta>
          <Link href="https://github.com/Noxx-7/Rahul-Sharma.github.io">
            Repository{' '}
            <ExternalLinkIcon mx="2px" />
          </Link>
        </ListItem>
        <ListItem>
          <Meta>Stack</Meta>
          <span>Html, Css, Javascript</span>
        </ListItem>
      </List>

      

      <iframe width="560" height="315" src="https://www.youtube.com/embed/7xKayY-yA3s?start=3" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>




    </Container>
  </Layout>
)

export default Work
export { getServerSideProps } from '../../components/chakra'
