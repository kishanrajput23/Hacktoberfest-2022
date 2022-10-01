import { Container, Heading, SimpleGrid } from '@chakra-ui/react'
import Layout from '../components/layouts/article'
import Section from '../components/section'
import { GridItem } from '../components/grid-item'

import thumbPortfolio from '../public/images/contents/youtube-how-to-build-portfolio.jpg'
import thumbHowToUseInkdrop from '../public/images/contents/youtube-how-to-use-inkdrop.jpg'
import thumbFishWorkflow from '../public/images/contents/youtube-fish-workflow.jpg'
import thumbMyDeskSetup from '../public/images/contents/youtube-my-desk-setup.jpg'


const Posts = () => (
  <Layout title="Posts">
    <Container>
      <Heading as="h3" fontSize={20} mb={4}>
        Popular Posts
      </Heading>

      <Section delay={0.1}>
        <SimpleGrid columns={[1, 2, 2]} gap={6}>
          <GridItem
            title="My Latest Vlogs"
            thumbnail={thumbPortfolio}
            href="https://youtu.be/nec6m3DD2jo"
          />
          <GridItem
            title="Counter Strike"
            thumbnail={thumbHowToUseInkdrop}
            href="https://youtu.be/cMh82J0-2S4"
          />
          <GridItem
            title="Set up PowerShell prompt with Oh My Posh"
            thumbnail={thumbFishWorkflow}
            href="https://youtu.be/ufEvuVnf_dE"
          />
          <GridItem
            title="My Todo App"
            thumbnail={thumbMyDeskSetup}
            href="https://youtu.be/NB7nzY_o0cs"
          />
        </SimpleGrid>
      </Section>

      
    </Container>
  </Layout>
)

export default Posts
export { getServerSideProps } from '../components/chakra'
