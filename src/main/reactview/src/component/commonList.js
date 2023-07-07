import { Table } from "react-bootstrap";

function CommonList({ posts }) {
    return (
        <Table striped bordered hover>
            <thead>
                <tr>
                    <th>말머리</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일</th>
                    <th>조회</th>
                    <th>좋아요</th>
                </tr>
            </thead>
            <tbody>
                {posts.map((post) => (
                    <tr key={post.id}>
                        <td>{post.id}</td>
                        <td>{post.title}</td>
                        <td>{post.user.name}</td>
                        <td>{post.createdDate}</td>
                        <td>{post.visit_count}</td>
                        <td>{post.interesting}</td>
                    </tr>
                ))}
            </tbody>
        </Table>
    );
}

export default CommonList;
