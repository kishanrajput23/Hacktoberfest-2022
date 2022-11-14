import { Container, Heading, SimpleGrid } from '@chakra-ui/react'
import Layout from '../components/layouts/article'
import Section from '../components/section'
import { WorkGridItem } from '../components/grid-item'

import thumbInkdrop from '../public/images/works/todo_app.png'
import thumbWalknote from '../public/images/works/walknote_eyecatch.png'
import thumbFourPainters from '../public/images/works/the-four-painters_eyecatch.jpg'


const Works = () => (
  <Layout title="Works">
    <Container>
      <Heading as="h3" fontSize={20} mb={4}>
        Works
      </Heading>

      <SimpleGrid columns={[1, 1, 2]} gap={6}>
        <Section>
          <WorkGridItem id="inkdrop" title="Todo App" thumbnail={thumbInkdrop}>
            Its a Todo-App made with react
          </WorkGridItem>
        </Section>
        <Section>
          <WorkGridItem
            id="walknote"
            title="First WebSite"
            thumbnail={thumbWalknote}
          >
            This was my first WebSite
          </WorkGridItem>
        </Section>

        <Section delay={0.1}>
          <WorkGridItem
            id="fourpainters"
            title="Java Quiz Software"
            thumbnail={thumbFourPainters}
          >
            This is a Quiz software
          </WorkGridItem>
        </Section>
       
      </SimpleGrid>

    
    </Container>
  </Layout>
)

export default Works
export { getServerSideProps } from '../components/chakra'
