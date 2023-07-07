import { Container, Row, Col, Image } from "react-bootstrap";
import { Link, useParams } from "react-router-dom";
import { useState, useEffect } from "react";
import axios from "axios";

function CommonDetail() {
    const { id } = useParams();
    const [post, setPost] = useState(null);
    const [user, setUser] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get(`/api/posts/${id}`);
                setPost(response.data.post);
                setUser(response.data.user);
            } catch (error) {
                console.error('An error occurred while fetching the post:', error);
            }
        };

        fetchData();
    }, [id]);

    if (!post || !user) {
        return <div>Loading...</div>; // or your custom loading indicator
    }

    return (
        <Container>
            <Row>
                <Col>
                    <h2>{post.title}</h2>
                </Col>
            </Row>
            <Row>
                <Col xs={1}>
                    <Image src={user.profileImage} roundedCircle />
                </Col>
                <Col>
                    {user.name}<br/>
                    {post.createdDate} {post.visit_count}
                </Col>
            </Row>
            <hr />
            <Row>
                <Col>
                    {post.content}
                </Col>
            </Row>
            <hr />
            <Row>
                <Col>
                    <Link to={`/user/${user.id}`}>{user.name}님의 게시글 더보기 ></Link>
                </Col>
            </Row>
        </Container>
    );
}

export default CommonDetail;
