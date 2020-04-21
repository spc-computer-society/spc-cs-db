#pragma once

#include <cmath>

#ifndef M_PI
#define M_PI 3.14159265358979323846
#endif

template <typename T>
class Vec2 {
public:

	T x, y;

	Vec2() :x(0), y(0) {}
	Vec2(T x, T y) : x(x), y(y) {}
	Vec2(const Vec2& v) : x(v.x), y(v.y) {}

	Vec2& operator=(const Vec2& v)
	{
		x = v.x;
		y = v.y;
		return *this;
	}

	Vec2 operator+(const Vec2& v)
	{
		return Vec2(x + v.x, y + v.y);
	}
	Vec2 operator-(const Vec2& v)
	{
		return Vec2(x - v.x, y - v.y);
	}
	Vec2 operator*(const Vec2& v)
	{
		return Vec2(x * v.x, y * v.y);
	}
	Vec2 operator/(const Vec2& v)
	{
		return Vec2(x / v.x, y / v.y);
	}

	Vec2& operator+=(const Vec2& v)
	{
		x += v.x;
		y += v.y;
		return *this;
	}
	Vec2& operator-=(const Vec2& v)
	{
		x -= v.x;
		y -= v.y;
		return *this;
	}
	Vec2& operator*=(const Vec2& v)
	{
		x *= v.x;
		y *= v.y;
		return *this;
	}
	Vec2& operator/=(const Vec2& v)
	{
		x /= v.x;
		y /= v.y;
		return *this;
	}

	Vec2 operator+(T s)
	{
		return Vec2(x + s, y + s);
	}
	Vec2 operator-(T s)
	{
		return Vec2(x - s, y - s);
	}
	Vec2 operator*(T s)
	{
		return Vec2(x * s, y * s);
	}
	Vec2 operator/(T s)
	{
		return Vec2(x / s, y / s);
	}

	Vec2& operator+=(T s)
	{
		x += s;
		y += s;
		return *this;
	}
	Vec2& operator-=(T s)
	{
		x -= s;
		y -= s;
		return *this;
	}
	Vec2& operator*=(T s)
	{
		x *= s;
		y *= s;
		return *this;
	}
	Vec2& operator/=(T s)
	{
		x /= s;
		y /= s;
		return *this;
	}

	Vec2& operator++()
	{
		x += 1.0;
		y += 1.0;
		return *this;
	}
	Vec2& operator--()
	{
		x -= 1.0;
		y -= 1.0;
		return *this;
	}
	Vec2 operator++(int)
	{
		Vec2 tmp(x, y);
		x += 1.0;
		y += 1.0;
		return tmp;
	}
	Vec2 operator--(int)
	{
		Vec2 tmp(x, y);
		x -= 1.0;
		y -= 1.0;
		return tmp;
	}

	friend bool operator==(const Vec2& v1, const Vec2& v2)
	{
		return v1.x == v2.x && v1.y == v2.y;
	}
	friend bool operator!=(const Vec2& v1, const Vec2& v2)
	{
		return !(v1.x == v2.x && v1.y == v2.y);
	}

	void Set(T x, T y)
	{
		this->x = x;
		this->y = y;
	}

	void Rotate(T deg)
	{
		T theta = deg / 180.0 * M_PI;
		T c = cos(theta);
		T s = sin(theta);
		T tx = x * c - y * s;
		T ty = x * s + y * c;
		x = tx;
		y = ty;
	}

	Vec2& Normalize()
	{
		if (Magnitude() == 0) return *this;
		*this *= (1.0 / Magnitude());
		return *this;
	}

	Vec2 Ortho() const
	{
		return Vec2(y, -x);
	}

	T Distance(const Vec2& v) const
	{
		Vec2 d(v.x - x, v.y - y);
		return d.Magnitude();
	}

	double SqrMagnitude() const
	{
		return x * x + y * y;
	}
	double Magnitude() const
	{
		return std::sqrt(SqrMagnitude());
	}

	double Degree(bool flipYAxis = false)
	{
		if (flipYAxis) this->y *= -1;
		double degree = atan(this->x / this->y) * 180 / M_PI;
		if (this->y < 0) degree -= 180;
		return degree;
	}

	int Quadrant() const
	{
		if (x > 0)
		{
			if (y > 0)
			{
				return 1;
			}
			else
			{
				return 4;
			}
		}
		else
		{
			if (y > 0)
			{
				return 2;
			}
			else
			{
				return 3;
			}
		}
	}
	static Vec2 ReQuadrant(int quadrant)
	{
		switch (quadrant)
		{
			case 1:
				return Vec2(1, 1);
				break;
			case 2:
				return Vec2(-1, 1);
				break;
			case 3:
				return Vec2(-1, -1);
				break;
			case 4:
				return Vec2(1, -1);
				break;
			default:
				return Vec2::Zero();
				break;
		}
	}

	static Vec2 SplitComponents(T m, T deg, int quadrant = 1)
	{
		T theta = deg / 180.0 * M_PI;

		T c = m * cos(theta);
		T s = m * sin(theta);
		return Vec2(c, s) * ReQuadrant(quadrant);
	}

	static T Dot(const Vec2& v1, const Vec2& v2)
	{
		return v1.x * v2.x + v1.y * v2.y;
	}
	static T Cross(const Vec2& v1, const Vec2& v2)
	{
		return (v1.x * v2.y) - (v1.y * v2.x);
	}

	static Vec2 One()
	{
		return Vec2(1, 1);
	}
	static Vec2 Zero()
	{
		return Vec2(0, 0);
	}
	static Vec2 Up()
	{
		return Vec2(0, 1);
	}
	static Vec2 Down()
	{
		return Vec2(0, -1);
	}
	static Vec2 Left()
	{
		return Vec2(-1, 0);
	}
	static Vec2 Right()
	{
		return Vec2(1, 0);
	}
};

typedef Vec2<double> Vector2D;
typedef Vec2<float> Vector2Df;
typedef Vec2<int> Vector2Di;
