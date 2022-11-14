import {
  
  Container,
  Badge,
  Link,
  List,
  ListItem,
  
} from '@chakra-ui/react'
import { ExternalLinkIcon } from '@chakra-ui/icons'
import { Title, Meta } from '../../components/work'
import P from '../../components/paragraph'
import Layout from '../../components/layouts/article'

const Work = () => (
  <Layout title="Java Quiz Software">
    <Container>
      <Title>
      Java Quiz Software  <Badge>2021</Badge>
      </Title>
      <P>
      I made this project in my Sophomore Year. It is written in JAVA using Swing and Awt GUI framework. It is a simple Quiz application which requires candidates details and then the MCQ test follows. After the completion of test the result is displayed with candidates detail.


      </P>
      <List ml={4} my={4}>
        <ListItem>
          <Meta>Stack</Meta>
          <span>Java, Awt, Swing</span>
        </ListItem>
        <ListItem>
          <Meta>Source</Meta>
          <Link href="https://github.com/Noxx-7/java_quiz">
            Repository <ExternalLinkIcon mx="2px" />
          </Link>
        </ListItem>
       
      </List>

     


   


    </Container>
  </Layout>
)

export default Work
export { getServerSideProps } from '../../components/chakra'
